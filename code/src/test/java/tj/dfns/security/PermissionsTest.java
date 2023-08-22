package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.utils.RESTInvoker;

import java.io.IOException;
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class PermissionsTest {
    @Test
    void listPermissions() throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String permissions = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/permissions", headers);
        System.out.println(permissions);
    }
}
