package tj.dfns.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import tj.dfns.model.*;
import tj.dfns.utils.Credentials;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CreateWalletTest {
    private Map<String, String> createHeaders() throws JsonProcessingException {
        final Nonce nonce = new Nonce();
        final String jsonData = nonce.jsonURLencoded();

        final Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("X-DFNS-APPID", "ap-5cjd0-gkpc0-8vbreg28vehsungm");
        headers.put("X-DFNS-NONCE", jsonData);
        headers.put("Authorization", "Bearer " + Credentials.DEFAULT_TOKEN);
        headers.put("Accept", "application/json");

        return headers;
    }

    private String getChallenge() throws IOException, InterruptedException {
        System.out.println("// STEP ONE: GET THE DFNS CHALLENGE");

        final CreateWalletRequest createWalletRequest = new CreateWalletRequest("EthereumGoerli", "tj-eth-wallet-a");
        final String createWalletRequestJSON = Utils.stringify(Utils.toJSON(createWalletRequest, CreateWalletRequest.class));
        final CreateUserActionSignaturePayload createUserActionSignaturePayload =
                new CreateUserActionSignaturePayload(createWalletRequestJSON, "POST", "/wallets");
        final String createUserActionSignaturePayloadJSON = Utils.stringify(Utils.toJSON(createUserActionSignaturePayload, CreateUserActionSignaturePayload.class));
        System.out.println("createUserActionSignaturePayloadJSON: " + createUserActionSignaturePayloadJSON);

        final Map<String, String> headers = createHeaders();

        // https://docs.dfns.co/dfns-docs/advanced-topics/authentication/request-signing/key-signing-typescript
        final String dfnsChallenge = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/auth/action/init", headers, createUserActionSignaturePayloadJSON);
        System.out.println("dfnsChallenge: " + dfnsChallenge);

        return dfnsChallenge;
    }

    private String createUserActionPayload(final String dfnsChallenge) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, SignatureException, InvalidKeyException {
        System.out.println("// STEP TWO: SIGN THE CHALLENGE");

        final JsonObject jsonObject = Utils.asJsonObject(dfnsChallenge);
        final String challengeIdentifier = jsonObject.get("challengeIdentifier").getAsString();
        System.out.println("challengeIdentifier: " + challengeIdentifier);

        final ClientData clientData = new ClientData(dfnsChallenge);
        final String clientDataJSON = Utils.stringify(Utils.toJSON(clientData, ClientData.class));

        final PrivateKey privateKey = CryptoUtils.rsaPrivateKey("rsa2048-c3p0.pem");
        final byte[] signedClientData = CryptoUtils.sign(privateKey, clientDataJSON.getBytes(StandardCharsets.UTF_8));
        final String signedClientDataBase64 = Utils.toBase64URL(signedClientData);
        System.out.println("signedClientDataBase64: " + signedClientDataBase64);

        final String credId = Credentials.API_CREDENTIAL_ID;
        final String clientDataJSONBase64 = Utils.toBase64URL(clientDataJSON.getBytes(StandardCharsets.UTF_8));

        final UserActionPayload.CredentialAssertion credentialAssertion = new UserActionPayload.CredentialAssertion(
                credId,
                clientDataJSONBase64,
                signedClientDataBase64
        );

        final UserActionPayload.FirstFactor firstFactor = new UserActionPayload.FirstFactor(credentialAssertion);
        final UserActionPayload userActionPayload = new UserActionPayload(challengeIdentifier, firstFactor);
        final String userActionPayloadJSON = Utils.stringify(Utils.toJSON(userActionPayload, UserActionPayload.class));
        System.out.println("userActionPayloadJSON: " + userActionPayloadJSON);
        return userActionPayloadJSON;
    }

    private String getUserActionSignature(final String userActionPayloadJSON) throws IOException, InterruptedException {
        System.out.println("// STEP THREE: SEND THE SIGNED CHALLENGE");

        final Map<String, String> headers = createHeaders();
        final String userActionSignature = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/auth/action", headers, userActionPayloadJSON);
        System.out.println("userActionSignature: " + userActionSignature);
        return userActionSignature;
    }

    @Test
    void createWallet() throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, SignatureException, InvalidKeyException {
        final String dfnsChallenge = getChallenge();
        final String userActionPayloadJSON = createUserActionPayload(dfnsChallenge);
        final String userActionSignature = getUserActionSignature(userActionPayloadJSON);

        //  {"error":{"name":"UnauthorizedError","errorName":"Unauthorized","serviceName":"auth-management","message":"Unauthorized","causes":[],"shouldTriggerInvestigation":true}}
    }
}
