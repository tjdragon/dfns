package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.policies.common.UserKind;
import tj.dfns.gen.model.users.create.CreateUserRequest;
import tj.dfns.utils.RESTInvoker;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class ServiceAccountsTest {
    @Test
    void listServiceAccounts() throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String serviceAccounts = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/auth/service-accounts", headers);
        System.out.println(serviceAccounts);
    }
}
