package space.sotis.starmaa.web.services;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.mindrot.jbcrypt.BCrypt;
import org.nutz.dao.Dao;
import org.nutz.dao.DaoException;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import space.sotis.starmaa.util.RSAKeyPairGenerator;
import space.sotis.starmaa.web.models.ServiceResponse;
import space.sotis.starmaa.web.models.Users;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Map;

/**
 * @author x1ngyu
 * @since 2024/10/11
 * <p>
 * todo
 */
@IocBean
public class UserService {
    @Inject
    private Dao dao;

    //region 内部方法

    /**
     * 解密密码
     *
     * @param encryptedPassword 加密后的密码
     * @return 解密后的密码
     * @throws Exception 异常
     */
    private String decryptPassword(String encryptedPassword) throws Exception {
        PrivateKey privateKey = RSAKeyPairGenerator.getPrivateKey();
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    private String genPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
    //endregion

    /**
     * 注册<br>
     * Json示例：<br>
     * {"userId":"", "password":"", "email":""}<br>
     *
     * @param registerJson Json字符串
     * @return ServiceResponse<String><br>
     */
    public ServiceResponse<String> register(String registerJson) throws Exception {
        Users user = Json.fromJson(Users.class, registerJson);

        if (dao.fetch(Users.class, user.getUserId()) != null) {
            return ServiceResponse.failure("用户已存在。");
        }

        user.setPassword(genPassword(decryptPassword(user.getPassword())));
        if (dao.insert(user) == null) {
            throw new DaoException("插入失败。");
        } else {
            return ServiceResponse.success("注册成功。");
        }
    }

    /**
     * 登录<br>
     * Json示例：<br>
     * {"userId":"", "password":""}<br>
     *
     * @param loginJson Json字符串
     * @return ServiceResponse<SaResult><br>
     */
    public ServiceResponse<SaResult> login(String loginJson) throws Exception {
        Map<String, String> loginMap = Json.fromJson(Map.class, loginJson);
        String decryptedPassword = decryptPassword(loginMap.get("password"));
        int userId = Integer.parseInt(loginMap.get("userId"));
        Users user = dao.fetch(Users.class, userId);

        if (user == null) {
            return ServiceResponse.failure("用户不存在。", new SaResult(403, "用户不存在。", "用户不存在。"));
        }

        if (checkPassword(decryptedPassword, user.getPassword())) {
            StpUtil.login(userId);
            return ServiceResponse.success(SaResult.ok("登录成功。"));
        } else {
            return ServiceResponse.failure("密码错误。", new SaResult(403, "密码错误。", "密码错误。"));
        }
    }
}
