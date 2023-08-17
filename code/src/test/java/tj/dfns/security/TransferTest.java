package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.challenge.DfnsChallenge;
import tj.dfns.gen.model.transfers.NewTransfer;
import tj.dfns.gen.model.useractionsig.UserActionResult;
import tj.dfns.gen.model.useractionsig.UserActionSignature;
import tj.dfns.model.man.CreateWalletRequest;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class TransferTest {
    @Test
    void simpleTransfer() throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final NewTransfer newTransfer = new NewTransfer();
        newTransfer.setAmount("888000000");
        newTransfer.setKind("Native");
        newTransfer.setTo("0x286F036f725356e3f88b036A22282978E83E2277");

        final String actionPath = "/wallets/wa-3uiqg-q2jk1-878rompsd0ee6lul/transfers";

        final DfnsChallenge dfnsChallenge = Commons.getChallenge(newTransfer, actionPath);
        final UserActionSignature userActionSignature = Commons.createUserActionPayload(dfnsChallenge);
        final UserActionResult userActionResult = Commons.getUserActionSignature(userActionSignature);
        System.out.println("userActionResult: " + userActionResult);

        final String newTransferJSON = Utils.stringify(Utils.toJSON(newTransfer, NewTransfer.class));

        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + actionPath, headers, newTransferJSON);
        System.out.println("New Transfer Result " + result);
    }
}
