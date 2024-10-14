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
        System.out.println();

        String password = "123456";
        PublicKey publicKey = RSAKeyPairGenerator.getPublicKey();
        String encryptedPassword = pass(password, publicKey);
        System.out.println(encryptedPassword);

        PrivateKey privateKey = RSAKeyPairGenerator.getPrivateKey();
        String decryptedPassword = decryptPassword("GEceZf1pDFUJWDV53HMsux/dk1TgIkHYZW8L7OXpypUWux3xBiThcmwe3CFsKnx52KHt/uPkj5s1xGaEBZp9Ka8Q9HM7OrjioLnhgEl0MSPhoWoTtTuzkb73j3j70PshuhTinabyYY4y97KWr4F5UNHbMm7w6PQvSXMcWGDa8ibAPll4uhtaRnsMXgXBoHMlF8i45gHsN7k45ZDMgd1kz69IYmc6WhJqay6m8LbZ5+SpVla866WULjwjSqrvfuCg6+M+iMB6R3+9CxRCN9IFlrqejsGW3ri7y9M03Ao0XT+55iLzt5rHOKemN2qs3ctggP8tAPS3JydHbjC+I8O/Iw==", privateKey);
        System.out.println(decryptedPassword);
        System.out.println();

//        PublicKey keyFromString = getPublicKeyFromBase64String(RSAKeyPairGenerator.getPublicKeyString());
        PublicKey keyFromString = getPublicKeyFromBase64String("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnSMRU6Q7P/6cY/7oen9wH5IFpSpNKaXokospKC4ZD0vFXTI2n7ieUW5g2PfrrWe0dt+dK0CCEMhoyWV/duqTXpU6pfiFcJYKqR88XOV0KQJaaxSRJIOe5OzFCJGQm0VLx6lxVDRtZZfE4Jh1OU9iejXIGRvhd3wzvexraghmPvvvAqcs7HhB8hP+L2lBJKmUrz2ZiA7CLUKN1+eE1ju/zlhA9LRT52omNpeUrhvq2cBtiheQm8e8qNPAjyt8Ypin4+wJovZthBw4uhR2dlpIp2vzadQlehfrOt9Y6Ncma1Jkdcc0Bq9N28FQhqdwFkCIbtywyrWeme3MGgiTf49BiQIDAQAB");
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
