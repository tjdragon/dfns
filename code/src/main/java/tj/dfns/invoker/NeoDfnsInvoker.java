package tj.dfns.invoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import tj.dfns.gen.model.challenge.DfnsChallenge;
import tj.dfns.gen.model.useractionsig.CredentialAssertion;
import tj.dfns.gen.model.useractionsig.FirstFactor;
import tj.dfns.gen.model.useractionsig.UserActionResult;
import tj.dfns.gen.model.useractionsig.UserActionSignature;
import tj.dfns.model.man.ClientData;
import tj.dfns.model.man.CreateUserActionSignaturePayload;
import tj.dfns.model.man.Nonce;
import tj.dfns.security.CryptoUtils;
import tj.dfns.security.Method;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static tj.dfns.security.Commons.createHeaders;

/**
 * Learning from previous implementation.
 * This version allows the definition of a given service account
 */
public final class NeoDfnsInvoker {
    public static final String DFNS_APP_ID = "ap-5cjd0-gkpc0-8vbreg28vehsungm";
    public static final Map<String, String> TOKENS = new ConcurrentHashMap<>();

    public static final Map<String, String> KEYS = new ConcurrentHashMap<>();
    private final String serviceAccountId;

    static {
        TOKENS.put("C3P0", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhdXRoLmRmbnMubmluamEiLCJhdWQiOiJkZm5zOmF1dGg6dXNlciIsInN1YiI6Im9yLTM1dnIyLWVhM3VqLTlraXE2NXJkNDkxaWR2Z3MiLCJqdGkiOiJ1ai01bzE4YS03b2ZzZS05djdwNjk1NDY1ajBxZWZkIiwic2NvcGUiOiIiLCJwZXJtaXNzaW9ucyI6W10sImh0dHBzOi8vY3VzdG9tL3VzZXJuYW1lIjoiIiwiaHR0cHM6Ly9jdXN0b20vYXBwX21ldGFkYXRhIjp7InVzZXJJZCI6InRvLTFza2NsLWExamQ4LTh0a3EwZGNyNzRtYmo2cWIiLCJvcmdJZCI6Im9yLTM1dnIyLWVhM3VqLTlraXE2NXJkNDkxaWR2Z3MiLCJ0b2tlbktpbmQiOiJTZXJ2aWNlQWNjb3VudCJ9LCJpYXQiOjE2OTE2NTAzMTMsImV4cCI6MTc1NDcyMjMxM30.ouI_8YawfGyk4EdkLhuWCBl4Rj9-8rq7XMS64CnXgVM0pzOlcu--h-9KqgKRdHQIfjtrc3kLaZJV7ThsPq975Xaejokd4pBCdZlGn_6lI3QKpQSL-YClTaIyaxPtdr_FBodBr9SyBOTKYfRmmf5ubjP1ROwKqdOw2tWXIs_NrmKvI7cmDX4nq1h0FDJ1lyySQLxhRms6buQ0a7r14FzJEt44_3THBRpntgZMlLNGs_4tcuEGkyFzk8P6wiee0ySqDgqwUlKxPXmcksDecR6Jy-kVDXHYeYfOjtf4uty3r4wAcl0zqr-q0bGkluzm7GY4Ar9dOG5HdI-pJYfV7ZX9KQ");
        KEYS.put("C3P0", "rsa2048-c3p0.pem");
    }

    private NeoDfnsInvoker(final String serviceAccountId) {
        this.serviceAccountId = serviceAccountId;
    }

    public static NeoDfnsInvoker as(final String serviceAccountId) {
        return new NeoDfnsInvoker(serviceAccountId);
    }

    private Map<String, String> createHeaders() throws JsonProcessingException {
        final Nonce nonce = new Nonce();
        final String jsonData = nonce.jsonURLencoded();

        final Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("X-DFNS-APPID", DFNS_APP_ID);
        headers.put("X-DFNS-NONCE", jsonData);
        headers.put("Authorization", "Bearer " + TOKENS.get(serviceAccountId));
        headers.put("Accept", "application/json");

        return headers;
    }

    private <T> DfnsChallenge getChallenge(final T t, final String userActionPath, final Method method) throws IOException, InterruptedException {
        System.out.println();
        System.out.println("NeoDfnsInvoker // STEP ONE: GET THE DFNS CHALLENGE");

        final String json = Utils.stringify(Utils.toJSON(t, t.getClass()));
        final CreateUserActionSignaturePayload createUserActionSignaturePayload = new CreateUserActionSignaturePayload(json, method.name(), userActionPath);
        final String createUserActionSignaturePayloadJSON = Utils.stringify(Utils.toJSON(createUserActionSignaturePayload, CreateUserActionSignaturePayload.class));
        System.out.println("createUserActionSignaturePayloadJSON: " + createUserActionSignaturePayloadJSON);

        final Map<String, String> headers = createHeaders();

        final String dfnsChallenge = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/auth/action/init", headers, createUserActionSignaturePayloadJSON);
        System.out.println("dfnsChallenge: " + dfnsChallenge);

        return Utils.fromJSON(dfnsChallenge, DfnsChallenge.class);
    }

    private UserActionSignature createUserActionPayload(final DfnsChallenge dfnsChallenge) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, SignatureException, InvalidKeyException {
        System.out.println();
        System.out.println("NeoDfnsInvoker // STEP TWO: SIGN THE CHALLENGE");

        // Get challenge identifier and credential id
        final String credId = dfnsChallenge.getAllowCredentials().getKey().get(0).getId();
        System.out.println("credId: " + credId);
        final String challengeIdentifier = dfnsChallenge.getChallengeIdentifier();
        System.out.println("challengeIdentifier: " + challengeIdentifier);
        final String challenge = dfnsChallenge.getChallenge();
        System.out.println("challenge: " + challenge);

        // Build the UserActionSignature payload
        final ClientData clientData = new ClientData(challenge);
        final String clientDataJSON = Utils.stringify(Utils.toJSON(clientData, ClientData.class));
        System.out.println("clientDataJSON (s): " + clientDataJSON);

        final PrivateKey privateKey = CryptoUtils.rsaPrivateKey(KEYS.get(serviceAccountId));
        final byte[] signatureRaw = CryptoUtils.sign(privateKey, clientDataJSON.getBytes(StandardCharsets.UTF_8));
        final String signatureBase64 = Utils.toBase64URL(signatureRaw);
        System.out.println("signatureBase64: " + signatureBase64);

        final UserActionSignature userActionSignature = new UserActionSignature();
        userActionSignature.setChallengeIdentifier(challengeIdentifier);

        final CredentialAssertion credentialAssertion = new CredentialAssertion();
        credentialAssertion.setCredId(credId);
        credentialAssertion.setClientData(Utils.toBase64URL(clientDataJSON.getBytes(StandardCharsets.UTF_8)));
        credentialAssertion.setSignature(signatureBase64);

        final FirstFactor firstFactor = new FirstFactor();
        firstFactor.setKind("Key");
        firstFactor.setCredentialAssertion(credentialAssertion);

        userActionSignature.setFirstFactor(firstFactor);

        return userActionSignature;
    }

    private UserActionResult getUserActionSignature(final UserActionSignature userActionSignature) throws IOException, InterruptedException {
        System.out.println();
        System.out.println("NeoDfnsInvoker // STEP THREE: SEND THE SIGNED CHALLENGE");

        final String userActionSignatureJSON = Utils.stringify(Utils.toJSON(userActionSignature, UserActionSignature.class));
        System.out.println("userActionSignatureJSON: " + userActionSignatureJSON);

        final Map<String, String> headers = createHeaders();
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/auth/action", headers, userActionSignatureJSON);
        System.out.println("result: " + result);
        return Utils.fromJSON(result, UserActionResult.class);
    }

    public <T> String post(final T data, final String path) throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final DfnsChallenge challenge = getChallenge(data, path, Method.POST);
        final UserActionSignature userActionSignature = createUserActionPayload(challenge);
        final UserActionResult userActionResult = getUserActionSignature(userActionSignature);

        final String json = Utils.stringify(Utils.toJSON(data, data.getClass()));
        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + path, headers, json);
        return result;
    }

    public <T> String put(final T data, final String path) throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final DfnsChallenge challenge = getChallenge(data, path, Method.PUT);
        final UserActionSignature userActionSignature = createUserActionPayload(challenge);
        final UserActionResult userActionResult = getUserActionSignature(userActionSignature);

        final String json = Utils.stringify(Utils.toJSON(data, data.getClass()));
        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.put(RESTInvoker.DEFAULT_ENDPOINT + path, headers, json);
        return result;
    }

    public String get(final String path) throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String result = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + path, headers);
        return result;
    }
}
