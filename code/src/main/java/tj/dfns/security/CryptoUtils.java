package tj.dfns.security;

import com.google.common.io.Resources;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;

public final class CryptoUtils {
    private static final String RSA_KEY_ALGO = "RSA";
    private static final String BEGIN_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----";
    private static final String END_PRIVATE_KEY = "-----END PRIVATE KEY-----";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static PrivateKey rsaPrivateKey(final String pemResource) throws NoSuchAlgorithmException, NoSuchProviderException, IOException, InvalidKeySpecException {
        final KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGO, "BC");
        final List<String> pemData = resourceAsListOfStrings(pemResource);
        final String keyData = pemToString(pemData);
        final var privateBytes = Base64.getDecoder().decode(keyData);
        final var keySpec = new PKCS8EncodedKeySpec(privateBytes);
        return keyFactory.generatePrivate(keySpec);
    }

    public static byte[] sign(final PrivateKey privateKey, final byte[] data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        final Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    public static List<String> resourceAsListOfStrings(final String resourceName) throws IOException {
        final var url = Resources.getResource(resourceName);
        return Resources.readLines(url, StandardCharsets.UTF_8);
    }

    public static String pemToString(List<String> pem) {
        final var sb = new StringBuilder();
        for (var i = 1; i < pem.size() - 1; i++) {
            sb.append(pem.get(i));
        }

        var keyData = sb.toString();
        keyData = keyData.replace(BEGIN_PRIVATE_KEY, "");
        keyData = keyData.replace(END_PRIVATE_KEY, "");
        keyData = keyData.replaceAll(System.lineSeparator(), "");

        return keyData;
    }
}
