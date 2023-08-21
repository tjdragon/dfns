package tj.dfns.security;

import org.junit.jupiter.api.Test;
import tj.dfns.gen.model.challenge.DfnsChallenge;
import tj.dfns.gen.model.policies.create.Configuration;
import tj.dfns.gen.model.policies.create.Kind;
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

public class SafelistPolicy {
    @Test
    // https://docs.dfns.co/dfns-docs/api-docs/policy-management/policy-rules/createpolicyrule
    void createPolicyRule() throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final NewPolicy safelistPolicyRule = new NewPolicy();
        safelistPolicyRule.setName("Safelist Rule");
        safelistPolicyRule.setDescription("Only allows known addresses");

        final Configuration configuration = new Configuration();
        configuration.setKind(Kind.AlwaysActivatedRule.name());
//        configuration.setCurrency("USD");
//        configuration.setLimit("0");
//        configuration.setAssetSymbol("BTC");
//        configuration.setShouldIgnoreAssetsWithoutMarketValue(true);

        safelistPolicyRule.setConfiguration(configuration);
        System.out.println("POL:: " + Utils.toJSON(safelistPolicyRule, safelistPolicyRule.getClass()));

        final DfnsChallenge challenge = Commons.getChallenge(safelistPolicyRule, "/policies/policy-rules/");
        final UserActionSignature userActionSignature = Commons.createUserActionPayload(challenge);
        final UserActionResult userActionResult = Commons.getUserActionSignature(userActionSignature);

        final String json = Utils.stringify(Utils.toJSON(safelistPolicyRule, safelistPolicyRule.getClass()));
        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + "/policies/policy-rules/", headers, json);
        System.out.println("Policy Creation Result " + result);
    }
    @Test
    void createPolicyControl() {}
    @Test
    void createPolicy() {}
}
