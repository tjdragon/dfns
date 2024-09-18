package tj.dfns.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import tj.dfns.gen.model.challenge.DfnsChallenge;
import tj.dfns.gen.model.useractionsig.CredentialAssertion;
import tj.dfns.gen.model.useractionsig.FirstFactor;
import tj.dfns.gen.model.useractionsig.UserActionResult;
import tj.dfns.gen.model.useractionsig.UserActionSignature;
import tj.dfns.model.man.ClientData;
import tj.dfns.model.man.CreateUserActionSignaturePayload;
import tj.dfns.model.man.Nonce;
import tj.dfns.utils.Credentials;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Commons {
    public static Map<String, String> createHeaders() throws JsonProcessingException {
        final Nonce nonce = new Nonce();
        final String jsonData = nonce.jsonURLencoded();

        final Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("X-DFNS-APPID", "ap-42qu7-ouhuu-8tr9sq2knl4heb18");
        headers.put("X-DFNS-NONCE", jsonData);
        headers.put("Authorization", "Bearer " + Credentials.DEFAULT_TOKEN);
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");

        return headers;
    }

    public static <T> DfnsChallenge getChallenge(final T t, final String userActionPath, final Method method) throws IOException, InterruptedException {
        System.out.println();
        System.out.println("// STEP ONE: GET THE DFNS CHALLENGE");

        final String json = Utils.stringify(Utils.toJSON(t, t.getClass()));
        final CreateUserActionSignaturePayload createUserActionSignaturePayload = new CreateUserActionSignaturePayload(json, method.name(), userActionPath);
        final String createUserActionSignaturePayloadJSON = Utils.stringify(Utils.toJSON(createUserActionSignaturePayload, CreateUserActionSignaturePayload.class));
        System.out.println("createUserActionSignaturePayloadJSON: " + createUserActionSignaturePayloadJSON);

        final Map<String, String> headers = createHeaders();

        final String dfnsChallenge = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/auth/action/init", headers, createUserActionSignaturePayloadJSON);
        System.out.println("dfnsChallenge: " + dfnsChallenge);

        return Utils.fromJSON(dfnsChallenge, DfnsChallenge.class);
    }

    // This object as JSON is compatible with https://docs.dfns.co/dfns-docs/api-docs/authentication/user-action-signing/completeuseractionsigning
    public static UserActionSignature createUserActionPayload(final DfnsChallenge dfnsChallenge) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, SignatureException, InvalidKeyException {
        System.out.println();
        System.out.println("// STEP TWO: SIGN THE CHALLENGE");

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
        System.out.println("clientDataJSON: " + Utils.toJSON(clientData, ClientData.class));
        System.out.println("clientDataJSON (s): " + clientDataJSON);

        final PrivateKey privateKey = CryptoUtils.rsaPrivateKey("rsa2048-c3p0.pem");
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

    public static UserActionResult getUserActionSignature(final UserActionSignature userActionSignature) throws IOException, InterruptedException {
        System.out.println();
        System.out.println("// STEP THREE: SEND THE SIGNED CHALLENGE");

        final String userActionSignatureJSON = Utils.stringify(Utils.toJSON(userActionSignature, UserActionSignature.class));
        System.out.println("userActionSignatureJSON: " + userActionSignatureJSON);

        final Map<String, String> headers = createHeaders();
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/auth/action", headers, userActionSignatureJSON);
        System.out.println("result: " + result);
        return Utils.fromJSON(result, UserActionResult.class);
    }
}
