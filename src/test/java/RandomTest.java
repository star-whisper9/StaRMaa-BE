import space.sotis.starmaa.util.RSAKeyPairGenerator;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author x1ngyu
 * @since 2024/10/11
 */

public class RandomTest {
    public static void main(String[] args) throws Exception {
        System.out.println(RSAKeyPairGenerator.getPublicKeyString());

        String password = "123456";
        PublicKey publicKey = RSAKeyPairGenerator.getPublicKey();
        String encryptedPassword = pass(password, publicKey);
        System.out.println(encryptedPassword);

        PrivateKey privateKey = RSAKeyPairGenerator.getPrivateKey();
        String decryptedPassword = decryptPassword(encryptedPassword, privateKey);
        System.out.println(decryptedPassword);

        PublicKey keyFromString = getPublicKeyFromBase64String(RSAKeyPairGenerator.getPublicKeyString());
        String encryptedPassword2 = pass(password, keyFromString);
        System.out.println(encryptedPassword2);

        byte[] keyBytes = publicKey.getEncoded();
        byte[] keyBytes2 = keyFromString.getEncoded();
        System.out.println(java.util.Arrays.equals(keyBytes, keyBytes2));

//        UserService userService = new UserService();
//        System.out.println(userService.decryptPassword(encryptedPassword));
    }

    private static String pass(String password, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decryptPassword(String encryptedPassword, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    private static PublicKey getPublicKeyFromBase64String(String base64PublicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }
}
