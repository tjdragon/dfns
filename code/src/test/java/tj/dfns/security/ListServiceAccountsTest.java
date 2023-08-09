package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.model.Nonce;
import tj.dfns.utils.RESTInvoker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListServiceAccountsTest {
    @Test
    public void listServiceAccounts() throws Exception {
        final Nonce nonce = new Nonce();
        final String jsonData = nonce.jsonURLencoded();
        System.out.println("jsonData: " + jsonData);

        final Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("X-DFNS-APPID", "ap-5cjd0-gkpc0-8vbreg28vehsungm");
        headers.put("X-DFNS-NONCE", jsonData);
        headers.put("Authorization", "Bearer " + RESTInvoker.DEFAULT_TOKEN);
        headers.put("Accept", "application/json");

        final String serviceAccounts = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/auth/service-accounts", headers);
        System.out.println(serviceAccounts);
    }
}
