package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.challenge.DfnsChallenge;
import tj.dfns.gen.model.useractionsig.CredentialAssertion;
import tj.dfns.gen.model.useractionsig.UserActionResult;
import tj.dfns.gen.model.useractionsig.UserActionSignature;
import tj.dfns.model.man.ClientData;
import tj.dfns.model.man.CreateUserActionSignaturePayload;
import tj.dfns.model.man.CreateWalletRequest;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class CreateWalletTest {
    static final String walletId = "tjs-wallet-1";

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
        final CreateWalletRequest createWalletRequest = new CreateWalletRequest("EthereumGoerli", walletId);
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

        final CreateWalletRequest createWalletRequest = new CreateWalletRequest("EthereumGoerli", walletId);

        final DfnsChallenge dfnsChallenge = Commons.getChallenge(createWalletRequest,"/wallets", Method.POST);
        final UserActionSignature userActionSignature = Commons.createUserActionPayload(dfnsChallenge);
        verify(userActionSignature);
        final UserActionResult userActionResult = Commons.getUserActionSignature(userActionSignature);
        System.out.println("userActionResult: " + userActionResult);
        postCreateWallet(userActionResult);
    }
}
