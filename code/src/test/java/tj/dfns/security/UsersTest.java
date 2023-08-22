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

public class UsersTest {
    @Test
    void listUsers() throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String users = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/auth/users", headers);
        System.out.println(users);
    }
    @Test
    void createUser() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException, InterruptedException {
        final CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("c3po@zodia.io");
        createUserRequest.setKind(UserKind.CustomerEmployee.name());

        final String result = DfnsInvoker.post(createUserRequest, "/auth/users");
        System.out.println("User Creation Result: " + result);
    }
}
