package tj.dfns.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.challenge.DfnsChallenge;
import tj.dfns.gen.model.useractionsig.CredentialAssertion;
import tj.dfns.gen.model.useractionsig.FirstFactor;
import tj.dfns.gen.model.useractionsig.UserActionResult;
import tj.dfns.gen.model.useractionsig.UserActionSignature;
import tj.dfns.model.man.*;
import tj.dfns.utils.Credentials;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static tj.dfns.security.Commons.createHeaders;

public class CreateWalletTest {

    // This is working
    private DfnsChallenge getChallenge() throws IOException, InterruptedException {
        System.out.println();
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

        return Utils.fromJSON(dfnsChallenge, DfnsChallenge.class);
    }

    // This object as JSON is compatible with https://docs.dfns.co/dfns-docs/api-docs/authentication/user-action-signing/completeuseractionsigning
    private UserActionSignature createUserActionPayload(final DfnsChallenge dfnsChallenge) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, SignatureException, InvalidKeyException {
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

    private UserActionResult getUserActionSignature(final UserActionSignature userActionSignature) throws IOException, InterruptedException {
        System.out.println();
        System.out.println("// STEP THREE: SEND THE SIGNED CHALLENGE");

        final String userActionSignatureJSON = Utils.stringify(Utils.toJSON(userActionSignature, UserActionSignature.class));
        System.out.println("userActionSignatureJSON: " + userActionSignatureJSON);

        final Map<String, String> headers = createHeaders();
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/auth/action", headers, userActionSignatureJSON);
        System.out.println("result: " + result);
        return Utils.fromJSON(result, UserActionResult.class);
    }

    private void verify(final UserActionSignature userActionSignature) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException, SignatureException, InvalidKeyException {
        // What needs to be verified - is actually what was signed, i.e, the Client Data.
        final CredentialAssertion credentialAssertion = userActionSignature.getFirstFactor().getCredentialAssertion();
        final String clientDataStr = credentialAssertion.getClientData(); // Base64 URL encoded
        final byte[] clientDataDecoded = Utils.fromBase64URL(clientDataStr);
        final ClientData clientDataObject = Utils.fromJSON(new String(clientDataDecoded), ClientData.class);
        System.out.println(clientDataObject);

        final PublicKey publicKey = CryptoUtils.rsaPublicKey("rsa2048-c3p0.public.pem");
        final byte[] signature = Utils.fromBase64URL(credentialAssertion.getSignature());
        final boolean verified = CryptoUtils.verify(publicKey, clientDataDecoded, signature);
        System.out.println("Verified? " + verified);
        assert verified;
    }

    private void postCreateWallet(final UserActionResult userActionResult) throws IOException, InterruptedException {
        final CreateWalletRequest createWalletRequest = new CreateWalletRequest("EthereumGoerli", "tj-eth-wallet-a");
        final String createWalletRequestJSON = Utils.stringify(Utils.toJSON(createWalletRequest, CreateWalletRequest.class));

        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/wallets", headers, createWalletRequestJSON);
        System.out.println("Wallet Creation Result " + result);
    }

    @Test
    void createWallet() throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException, SignatureException, InvalidKeyException {
        System.out.println();
        System.out.println("-- CREATE WALLET");

        final DfnsChallenge dfnsChallenge = getChallenge();
        final UserActionSignature userActionSignature = createUserActionPayload(dfnsChallenge);
        verify(userActionSignature);
        final UserActionResult userActionResult = getUserActionSignature(userActionSignature);
        System.out.println("userActionResult: " + userActionResult);
        postCreateWallet(userActionResult);
    }
}
