package tj.dfns.security;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

public class RSATest {
    @Test
    void readRSAKey() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchProviderException {
        final PrivateKey privateKey = CryptoUtils.rsaPrivateKey("rsa2048-c3p0.pem");
        System.out.println(privateKey);
    }
}
