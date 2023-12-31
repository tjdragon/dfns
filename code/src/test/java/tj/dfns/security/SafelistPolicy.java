package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.challenge.DfnsChallenge;
import tj.dfns.gen.model.policies.common.ActivityKind;
import tj.dfns.gen.model.policies.common.Status;
import tj.dfns.gen.model.policies.control.NewControl;
import tj.dfns.gen.model.policies.common.Kind;
import tj.dfns.gen.model.policies.create.NewPolicy;
import tj.dfns.gen.model.policies.execution.PolicyControlExec;
import tj.dfns.gen.model.useractionsig.UserActionResult;
import tj.dfns.gen.model.useractionsig.UserActionSignature;
import tj.dfns.utils.RESTInvoker;
import tj.dfns.utils.Utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class SafelistPolicy {
    @Test
    // https://docs.dfns.co/dfns-docs/api-docs/policy-management/policy-rules/createpolicyrule
    void createPolicyRule() throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final NewPolicy safelistPolicyRule = new NewPolicy();
        safelistPolicyRule.setName("Safelist Rule");
        safelistPolicyRule.setDescription("Only allows known addresses");

        final tj.dfns.gen.model.policies.create.Configuration configuration = new tj.dfns.gen.model.policies.create.Configuration();
        configuration.setKind(Kind.AlwaysActivated.name());

        safelistPolicyRule.setConfiguration(configuration);

        final DfnsChallenge challenge = Commons.getChallenge(safelistPolicyRule, "/policies/policy-rules/", Method.POST);
        final UserActionSignature userActionSignature = Commons.createUserActionPayload(challenge);
        final UserActionResult userActionResult = Commons.getUserActionSignature(userActionSignature);

        final String json = Utils.stringify(Utils.toJSON(safelistPolicyRule, safelistPolicyRule.getClass()));
        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/policies/policy-rules/", headers, json);
        System.out.println("Policy Creation Result: " + result);
    }
    @Test
    void createPolicyControl() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException, InterruptedException {
        final NewControl safeListPolicyControl = new NewControl();
        safeListPolicyControl.setName("Safelist Control");
        safeListPolicyControl.setDescription("Whitelist Compliance Control");

        final tj.dfns.gen.model.policies.control.Configuration configuration = new tj.dfns.gen.model.policies.control.Configuration();
        configuration.setNumApprovals(1);
        configuration.setTimeoutInMinutes(60);
        configuration.setKind(Kind.RequestApproval.name());
        configuration.setApproverUsernames(Arrays.asList("us-3jdgt-ptdnn-94d9j0kbrck2rg5h"));  // c3po

        safeListPolicyControl.setConfiguration(configuration);
        System.out.println("POL:: " + Utils.toJSON(safeListPolicyControl, safeListPolicyControl.getClass()));

        final String result = DfnsInvoker.post(safeListPolicyControl, "/policies/policy-controls");
        System.out.println("Policy Control Result: " + result);
    }
    @Test
    void createPolicy() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException, InterruptedException {
        final tj.dfns.gen.model.policies.neo.NewPolicy newPolicy = new tj.dfns.gen.model.policies.neo.NewPolicy();
        newPolicy.setActivityKind(ActivityKind.WalletsTransferAsset.name());
        newPolicy.setDescription("Only allow known addresses in/out");
        newPolicy.setName("AML Safelist Policy");
        newPolicy.setRuleIds(Arrays.asList("pr-king-arkan-72glofqqij9qlo65"));
        newPolicy.setControlIds(Arrays.asList("pc-finch-lacto-45mcole8h99c1oa5"));
        newPolicy.setStatus("Enabled");
        newPolicy.setFilter(null); // Applies to all
        System.out.println("POL:: " + Utils.toJSON(newPolicy, newPolicy.getClass()));

        final String result = DfnsInvoker.post(newPolicy, "/policies");
        System.out.println("Policy Result: " + result);
    }

    @Test
    void approveOrRejectPolicyControl() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException, InterruptedException {
        final PolicyControlExec policyControlExec = new PolicyControlExec();
        policyControlExec.setStatus(Status.Passed.name());
        System.out.println("POL:: " + Utils.toJSON(policyControlExec, policyControlExec.getClass()));

        final String result = DfnsInvoker.put(policyControlExec, "/policies/policy-control-executions/pce-idaho-nebra-32mq5316bl8auqcu");
        System.out.println("Policy Control Execution: " + result);
    }
}
