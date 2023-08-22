package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.assignment.create.CreateAssignmentRequest;
import tj.dfns.gen.model.permissions.create.CreatePermissionRequest;
import tj.dfns.gen.model.policies.common.UserKind;
import tj.dfns.utils.RESTInvoker;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class PermissionsTest {
    @Test
    void listPermissions() throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String permissions = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/permissions", headers);
        System.out.println(permissions);
    }

    @Test
    void listAssignments() throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String permissions = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/permissions/assignments", headers);
        System.out.println(permissions);
    }

    @Test
    void createSafelistApproverPermission() throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final CreatePermissionRequest createPermissionRequest = new CreatePermissionRequest();
        createPermissionRequest.setName("Safelist Approver");
        createPermissionRequest.setOperations(Arrays.asList("PolicyControlExecutions:Update"));

        final String result = DfnsInvoker.post(createPermissionRequest, "/permissions");
        System.out.println("Permission Creation Result: " + result);
    }

    @Test
    void createAssignment() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException, InterruptedException {
        final CreateAssignmentRequest createAssignmentRequest = new CreateAssignmentRequest();
        createAssignmentRequest.setPermissionId("pm-beryl-kilo-15000sac4c91vbn9");
        createAssignmentRequest.setIdentityId("us-3jdgt-ptdnn-94d9j0kbrck2rg5h");
        createAssignmentRequest.setIdentityKind(UserKind.CustomerEmployee.name());

        final String result = DfnsInvoker.post(createAssignmentRequest, "/permissions/assignments");
        System.out.println("Assignment Creation Result: " + result);
    }
}
