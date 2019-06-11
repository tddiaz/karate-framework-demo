package apis;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ECCrypto {
    private static final Logger LOGGER = Logger.getLogger(ECCrypto.class.getName());

    private static final String SIGNATURE_ALGORITHM = "SHA256withECDSA";
    private static final String KEYPAIR_ALGORITHM = "EC";

    public static PrivateKey privateKeyFromBytes(byte[] key) {

        try {
            KeyFactory kf = KeyFactory.getInstance(KEYPAIR_ALGORITHM);
            return kf.generatePrivate(new PKCS8EncodedKeySpec(key));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.log(Level.ALL, "invalid private key {}", key);
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] sign(String text, PrivateKey privKey) {
        try {
            Signature ecdsa = Signature.getInstance(SIGNATURE_ALGORITHM);
            ecdsa.initSign(privKey);
            ecdsa.update(text.getBytes(StandardCharsets.UTF_8));
            return ecdsa.sign();
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
            LOGGER.log(Level.ALL, "sign", e);
        }
        return new byte[0];
    }
}
