package tj.dfns.security;

import tj.dfns.gen.model.challenge.DfnsChallenge;
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

public class DfnsInvoker {
    public static <T> String post(final T data, final String path) throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final DfnsChallenge challenge = Commons.getChallenge(data, path, Method.POST);
        final UserActionSignature userActionSignature = Commons.createUserActionPayload(challenge);
        final UserActionResult userActionResult = Commons.getUserActionSignature(userActionSignature);

        final String json = Utils.stringify(Utils.toJSON(data, data.getClass()));
        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.post(RESTInvoker.DEFAULT_ENDPOINT + path, headers, json);
        return result;
    }

    public static <T> String put(final T data, final String path) throws IOException, InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, NoSuchProviderException, InvalidKeyException {
        final DfnsChallenge challenge = Commons.getChallenge(data, path, Method.PUT);
        final UserActionSignature userActionSignature = Commons.createUserActionPayload(challenge);
        final UserActionResult userActionResult = Commons.getUserActionSignature(userActionSignature);

        final String json = Utils.stringify(Utils.toJSON(data, data.getClass()));
        final Map<String, String> headers = createHeaders();
        headers.put("X-DFNS-USERACTION", userActionResult.getUserAction());
        final String result = RESTInvoker.put(RESTInvoker.DEFAULT_ENDPOINT + path, headers, json);
        return result;
    }
}
