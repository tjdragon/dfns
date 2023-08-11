package tj.dfns.security;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class RSATest {
    @Test
    void readRSAPrivateKey() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException {
        final PrivateKey privateKey = CryptoUtils.rsaPrivateKey("rsa2048-c3p0.pem");
        System.out.println(privateKey);
    }

    @Test
    void readRSAPublicKey() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException {
        final PublicKey publicKey = CryptoUtils.rsaPublicKey("rsa2048-c3p0.public.pem");
        System.out.println(publicKey);
    }
    @Test
    void signVerify() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException, SignatureException, InvalidKeyException {
        // Sign
        final PrivateKey privateKey = CryptoUtils.rsaPrivateKey("rsa2048-c3p0.pem");
        final byte[] message = "dfns rocks!".getBytes(StandardCharsets.UTF_8);
        final byte[] signature = CryptoUtils.sign(privateKey, message);

        // Verify
        final PublicKey publicKey = CryptoUtils.rsaPublicKey("rsa2048-c3p0.public.pem");
        final boolean verified = CryptoUtils.verify(publicKey, message, signature);
        System.out.println("Verified? " + verified);
        assert verified;
    }
}
