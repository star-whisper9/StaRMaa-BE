package space.sotis.starmaa.web.controllers;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import space.sotis.starmaa.web.models.ServiceResponse;
import space.sotis.starmaa.web.services.UserService;

/**
 * @author x1ngyu
 * @since 2024/10/11
 * <p>
 * 用户控制器。<br>
 * <code>@Fail</code>默认的错误返回是Http 500。<br>
 * <code>@At("/api/user")</code>端点根路径。<br>
 */
@IocBean
@At("/api/user")
@Fail("http:500")
public class UserController {
    @Inject
    private UserService userService;

    /**
     * 注册端点
     *
     * @param registerJson Json字符串
     * @return ServiceResponse<String>
     * @see space.sotis.starmaa.web.services.UserService#register(String)
     */
    @At("/register")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    @Chain("anonymous")
    public ServiceResponse<String> register(@Param("..") String registerJson) throws Exception {
        return userService.register(registerJson);
    }

    /**
     * 从会话中获取用户信息<br>
     * 返回的Payload类似：<br>
     * <code>{\"userId\":1,\"email\":\"star@sotis.space\",\"phone\":null,\"username\":null,\"deviceId\":null,\"avatar\":null,\"birthday\":null,\"role\":\"guest\",\"remainDays\":0,\"createdAt\":\"2024-10-14 17:54:11\",\"updatedAt\":\"2024-10-14 17:54:11\"}</code><br>
     * 使用Json传递而不是对象传递因为对象在NutzJson返回时会忽略Null项，使用Tidy格式方便前端读取（避免换行的错误）。
     *
     * @return ServiceResponse<String> 包含用户对象的Tidy格式Json字符串，如果失败则返回默认的失败响应（无错误信息）
     */
    @At("/info")
    @GET
    @Ok("json")
    public ServiceResponse<String> getUserInfo() {
        return ServiceResponse.success(Json.toJson(StpUtil.getSession().get("user"), JsonFormat.tidy()));
    }

    /**
     * 设置单列用户信息
     *
     * @param userInfoJson Json字符串
     * @return ServiceResponse
     * @see space.sotis.starmaa.web.services.UserService#setUserInfo(String)
     */
    @At("/info")
    @PATCH
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public ServiceResponse<?> setUserInfo(@Param("..") String userInfoJson) {
        return userService.setUserInfo(userInfoJson);
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

    /**
     * 登出端点<br>
     * <code>@Ok</code>204 No Content
     */
    @At("/logout")
    @POST
    @Ok("http:204")
    public void logout() {
        userService.logout();
    }

    /**
     * todo 忘记密码的重置密码端点。
     * 考虑使用邮箱验证重置密码
     */
    @At("/password/reset")
    @PATCH
    @AdaptBy(type = JsonAdaptor.class)
    // 端点完成之后修改403响应
    @Ok("http:403")
    public void resetPassword() {
        System.out.println("resetPassword");
    }

    /**
     * 已知密码的修改密码端点。
     */
    @At("/password")
    @PATCH
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public ServiceResponse<String> changePassword(@Param("..") String passwordJson) throws Exception {
        return userService.changePassword(passwordJson);
    }

    /**
     * todo 头像上传
     */
    @At("/avatar")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void setAvatar() {
        System.out.println("setAvatar");
    }

    /**
     * todo 注销账户
     */
    @At("/deleteAccount")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void deleteAccount() {
        System.out.println("deleteAccount");
    }

    /**
     * todo 邮箱验证
     */
    @At("/verifyEmail")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void verifyEmail() {
        System.out.println("verifyEmail");
    }

    /**
     * todo 修改邮箱
     */
    @At("/changeEmail")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public void changeEmail() {
        System.out.println("changeEmail");
    }
}
