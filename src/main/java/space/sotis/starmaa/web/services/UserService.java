package space.sotis.starmaa.web.services;

import cn.dev33.satoken.session.SaSession;
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
     * 登录<br>登录完成后会将用户信息存入Session。<br>
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
            SaSession session = StpUtil.getSession();
            user.setPassword(null);
            session.set("user", user);
            Object n = session.get("user");
            return ServiceResponse.success(SaResult.ok("登录成功。"));
        } else {
            return ServiceResponse.failure("密码错误。", new SaResult(403, "密码错误。", "密码错误。"));
        }
    }

    /**
     * 单次修改单列用户信息
     *
     * @param userInfoJson Json字符串
     * @return ServiceResponse<String>
     */
    public ServiceResponse<String> setUserInfo(String userInfoJson) {
        /*
        允许修改的列
         */
        enum Column {
            phone,
            username,
            deviceId,
            birthday
        }

        Users user = (Users) StpUtil.getSession().get("user");
        Map<Object, Object> userInfoMap = Json.fromJson(Map.class, userInfoJson);
        if (userInfoMap.size() != 1) {
            throw new IllegalArgumentException("参数数量错误。");
        }

        String key = (String) userInfoMap.keySet().toArray()[0];
        try {
            Column.valueOf(key);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("不允许修改的字段: " + key);
        }

        String value = (String) userInfoMap.get(key);
        switch (key) {
            case "phone":
                user.setPhone(value);
                break;
            case "username":
                user.setUsername(value);
                break;
            case "deviceId":
                user.setDeviceId(value);
                break;
            case "birthday":
                user.setBirthday(value);
                break;
        }

        if (dao.update(user, String.format("^%s$", key)) == 1) {
            return ServiceResponse.success("修改成功。");
        } else {
            return ServiceResponse.failure("修改失败。");
        }
    }

    /**
     * 退出登录
     */
    public void logout() {
        StpUtil.logout();
    }

    /**
     * 修改密码
     *
     * @param passwordJson 包含新旧密码的Json
     * @return ServiceResponse<String>
     */
    public ServiceResponse<String> changePassword(String passwordJson) throws Exception {
        Map<String, String> passwordMap = Json.fromJson(Map.class, passwordJson);
        SaSession session = StpUtil.getSession();
        Users user = (Users) session.get("user");
        int userId = user.getUserId();
        String oldPassword = decryptPassword(passwordMap.get("oldPassword"));
        String newPassword = decryptPassword(passwordMap.get("newPassword"));

        Users dbUser = dao.fetch(Users.class, userId);
        if (dbUser == null) {
            return ServiceResponse.failure("用户不存在。");
        }

        if (checkPassword(oldPassword, dbUser.getPassword())) {
            if (oldPassword.equals(newPassword)) {
                return ServiceResponse.failure("新旧密码相同。");
            }

            user.setPassword(genPassword(newPassword));
            if (dao.update(user, "^password$") == 1) {
                return ServiceResponse.success("修改成功。");
            } else {
                return ServiceResponse.failure("修改失败。");
            }
        } else {
            return ServiceResponse.failure("密码错误。");
        }
    }
}
