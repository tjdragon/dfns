package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.wallets.WalletAssets;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class WalletTest {
    static final String TJ_WALLET_ID = "wa-3uiqg-q2jk1-878rompsd0ee6lul";

    @Test
    void walletInfo() throws IOException, InterruptedException {
        // tj-eth-wallet-a has id wa-3uiqg-q2jk1-878rompsd0ee6lul
        final Map<String, String> headers = createHeaders();
        final String walletDetails = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/wallets/wa-3uiqg-q2jk1-878rompsd0ee6lul", headers);
        System.out.println(walletDetails);
    }

    @Test
    void listWalletAssets() throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String walletDetails = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/wallets/wa-3uiqg-q2jk1-878rompsd0ee6lul/assets", headers);
        final WalletAssets walletAssets = Utils.fromJSON(walletDetails, WalletAssets.class);
        System.out.println(walletDetails);
    }
}
