package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.utils.RESTInvoker;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListServiceAccounts {
    @Test
    public void listServiceAccounts() throws IOException, InterruptedException {
        final Nonce nonce = new Nonce();
        System.out.println(nonce.asJSON());

        final Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("X-DFNS-APPID", "ap-5cjd0-gkpc0-8vbreg28vehsungm");
        headers.put("X-DFNS-NONCE", nonce.asJSON());
        headers.put("Authorization", "Bearer " + RESTInvoker.DEFAULT_TOKEN);

        final String serviceAccounts = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/auth/service-accounts", headers);
        System.out.println(serviceAccounts);
    }
}
