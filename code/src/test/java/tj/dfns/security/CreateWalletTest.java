package tj.dfns.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import tj.dfns.model.CreateUserActionSignaturePayload;
import tj.dfns.model.CreateWalletRequest;
import tj.dfns.model.Nonce;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CreateWalletTest {
    @Test
    void createWallet() throws IOException, InterruptedException {
        final CreateWalletRequest createWalletRequest = new CreateWalletRequest("EthereumGoerli", "tj-eth-wallet-a");
        final String createWalletRequestJSON = Utils.stringify(Utils.toJSON(createWalletRequest, CreateWalletRequest.class));
        final CreateUserActionSignaturePayload createUserActionSignaturePayload =
                new CreateUserActionSignaturePayload(createWalletRequestJSON, "POST", "/wallets");
        final String createUserActionSignaturePayloadJSON = Utils.stringify(Utils.toJSON(createUserActionSignaturePayload, CreateUserActionSignaturePayload.class));
        System.out.println("createUserActionSignaturePayloadJSON: " + createUserActionSignaturePayloadJSON);

        final Nonce nonce = new Nonce();
        final String jsonData = nonce.jsonURLencoded();

        final Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("X-DFNS-APPID", "ap-5cjd0-gkpc0-8vbreg28vehsungm");
        headers.put("X-DFNS-NONCE", jsonData);
        headers.put("Authorization", "Bearer " + RESTInvoker.DEFAULT_TOKEN);
        headers.put("Accept", "application/json");

        // https://docs.dfns.co/dfns-docs/advanced-topics/authentication/request-signing/key-signing-typescript
        final String dfnsChallenge = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/auth/action/init", headers, createUserActionSignaturePayloadJSON);
        System.out.println("dfnsChallenge: " + dfnsChallenge);
    }
}
