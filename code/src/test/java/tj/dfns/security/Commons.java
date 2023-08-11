package tj.dfns.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import tj.dfns.model.man.Nonce;
import tj.dfns.utils.Credentials;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Commons {
    public static Map<String, String> createHeaders() throws JsonProcessingException {
        final Nonce nonce = new Nonce();
        final String jsonData = nonce.jsonURLencoded();

        final Map<String, String> headers = new ConcurrentHashMap<>();
        headers.put("X-DFNS-APPID", "ap-5cjd0-gkpc0-8vbreg28vehsungm");
        headers.put("X-DFNS-NONCE", jsonData);
        headers.put("Authorization", "Bearer " + Credentials.DEFAULT_TOKEN);
        headers.put("Accept", "application/json");

        return headers;
    }
}
