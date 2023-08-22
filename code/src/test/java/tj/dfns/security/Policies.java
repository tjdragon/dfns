package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.challenge.DfnsChallenge;
import tj.dfns.gen.model.policies.create.Configuration;
import tj.dfns.gen.model.policies.common.Kind;
import tj.dfns.gen.model.policies.create.NewPolicy;
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
import java.util.Map;

import static tj.dfns.security.Commons.createHeaders;

public class Policies {
    @Test
    void listPolicies() throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String policies = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/policies/policy-rules/", headers);
        System.out.println(policies); // {"items":[]} = empty policies
    }
    @Test
    void createAmountLimitRule() throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final NewPolicy amountLimitPolicy = new NewPolicy();
        amountLimitPolicy.setDescription("TRP Rule #2 Warning if above 10000 USD");
        amountLimitPolicy.setName("TRP Rule #2");

        final Configuration configuration = new Configuration();
        configuration.setKind(Kind.TransferAmountLimit.name());
        configuration.setLimit("10000");
        configuration.setCurrency("USD");

        amountLimitPolicy.setConfiguration(configuration);

        final DfnsChallenge challenge = Commons.getChallenge(amountLimitPolicy, "/policies/policy-rules/");
        final UserActionSignature userActionSignature = Commons.createUserActionPayload(challenge);
        final UserActionResult userActionResult = Commons.getUserActionSignature(userActionSignature);

        final String json = Utils.stringify(Utils.toJSON(amountLimitPolicy, amountLimitPolicy.getClass()));
        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/policies/policy-rules/", headers, json);
        System.out.println("Policy Creation Result " + result);
    }

    @Test
    void listPoliciesControls() throws IOException, InterruptedException {
        final Map<String, String> headers = createHeaders();
        final String controls = RESTInvoker.get(RESTInvoker.DEFAULT_ENDPOINT + "/policies/policy-controls/", headers);
        System.out.println(controls); // {"items":[]} = empty policies
    }
}
