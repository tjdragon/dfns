package tj.dfns.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import tj.dfns.utils.RESTInvoker;

import java.io.IOException;
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class WalletTest {
    @Test
    void walletInfo() throws IOException, InterruptedException {
        // tj-eth-wallet-a has id wa-3uiqg-q2jk1-878rompsd0ee6lul
        final Map<String, String> headers = createHeaders();
        final String walletDetails = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/wallets/wa-3uiqg-q2jk1-878rompsd0ee6lul", headers);
        System.out.println(walletDetails);
    }
}
