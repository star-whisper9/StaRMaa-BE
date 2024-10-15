package space.sotis.starmaa.web.controllers;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
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
@Fail("http:500")
public class SecurityController {
    /**
     * 获取公钥端点
     *
     * @return 公钥。字段名：<code>publicKey</code>
     */
    @At("/public-key")
    @GET
    @Ok("json")
    @Chain("anonymous")
    public Map<String, String> getPublicKey() {
        Map<String, String> map = new HashMap<>();
        map.put("publicKey", RSAKeyPairGenerator.getPublicKeyString());
        return map;
    }
}
