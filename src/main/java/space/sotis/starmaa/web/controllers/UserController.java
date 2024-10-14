package space.sotis.starmaa.web.controllers;

import cn.dev33.satoken.util.SaResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import space.sotis.starmaa.web.models.ServiceResponse;
import space.sotis.starmaa.web.services.UserService;

/**
 * @author x1ngyu
 * @since 2024/10/11
 * <p>
 * todo
 */
@IocBean
@At("/api/user")
public class UserController {
    @Inject
    private UserService userService;

    /**
     * 注册端点
     *
     * @param registerJson Json字符串
     * @return {@link ServiceResponse}的Tidy格式Json字符串
     * @see space.sotis.starmaa.web.services.UserService#register(String)
     */
    @At("/register")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    @Chain("anonymous")
    public ServiceResponse<?> register(@Param("..") String registerJson) throws Exception {
        ServiceResponse<String> response = userService.register(registerJson);
        return response != null ? response : new ServiceResponse<>();
    }

    @At("/getUserInfo")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void getUserInfo() {
        System.out.println("getUserInfo");
    }

    @At("/setUserInfo")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void setUserInfo() {
        System.out.println("updateUserInfo");
    }

    /**
     * 登录端点。
     *
     * @param loginJson Json字符串
     * @return {@link SaResult}对象
     * @see space.sotis.starmaa.web.services.UserService#login(String)
     */
    @At("/login")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    @Chain("anonymous")
    public SaResult login(@Param("..") String loginJson) throws Exception {
        ServiceResponse<SaResult> response = userService.login(loginJson);
        /*
        内部抛错的时候response为null，这里判空处理，抛错了返回Http 500。
         */
        if (response != null) {
            return response.getPayload();
        } else {
            return new SaResult(500, "Internal Server Error", "Internal Server Error");
        }
    }

    @At("/logout")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void logout() {
        System.out.println("logout");
    }

    @At("/resetPassword")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void resetPassword() {
        System.out.println("resetPassword");
    }

    @At("/changePassword")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void changePassword() {
        System.out.println("changePassword");
    }

    @At("/setAvatar")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void setAvatar() {
        System.out.println("setAvatar");
    }

    @At("/deleteAccount")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void deleteAccount() {
        System.out.println("deleteAccount");
    }

    @At("/verifyEmail")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void verifyEmail() {
        System.out.println("verifyEmail");
    }

    @At("/changeEmail")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void changeEmail() {
        System.out.println("changeEmail");
    }
}
