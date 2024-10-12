package space.sotis.starmaa.util;

import lombok.Getter;
import org.nutz.ioc.loader.annotation.IocBean;

import java.security.*;
import java.util.Base64;

/**
 * @author x1ngyu
 * @since 2024/10/12
 * <p>
 * Ioc管理类。<br>
 * RSA密钥对生成器。
 */
@Getter
@IocBean
public class RSAKeyPairGenerator {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public RSAKeyPairGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.publicKey = pair.getPublic();
        this.privateKey = pair.getPrivate();
    }

    public String getPublicKey() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
