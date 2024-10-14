package space.sotis.starmaa.util;

import lombok.Getter;

import java.security.*;
import java.util.Base64;

/**
 * @author x1ngyu
 * @since 2024/10/12
 * <p>
 * Ioc管理类。<br>
 * RSA密钥对生成器。
 */
public class RSAKeyPairGenerator {
    private static final PublicKey publicKey;
    @Getter
    private static final PrivateKey privateKey;
    private static final KeyPairGenerator keyGen;

    static {
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair pair = keyGen.generateKeyPair();
            publicKey = pair.getPublic();
            privateKey = pair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 重写的公钥获取方法，返回的是Base64编码后的公钥字符串。
     *
     * @return String 公钥字符串
     */
    public static String getPublicKey() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
