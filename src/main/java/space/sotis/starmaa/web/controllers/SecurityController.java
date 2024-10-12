package space.sotis.starmaa.web.controllers;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Chain;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;
import space.sotis.starmaa.util.RSAKeyPairGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author x1ngyu
 * @since 2024/10/12
 * <p>
 * 安全工具控制器。
 */
@IocBean
@At("/api/security")
public class SecurityController {
    @Inject
    private RSAKeyPairGenerator rsaKeyPairGenerator;

    /**
     * 获取公钥端点
     *
     * @return 公钥。字段名：<code>publicKey</code>
     */
    @At("/getPublicKey")
    @GET
    @Ok("json")
    @Chain("anonymous")
    public Map<String, String> getPublicKey() {
        Map<String, String> map = new HashMap<>();
        map.put("publicKey", rsaKeyPairGenerator.getPublicKey());
        return map;
    }
}
