package space.sotis.starmaa.internal.satoken;

import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.servlet.model.SaRequestForServlet;
import cn.dev33.satoken.servlet.model.SaResponseForServlet;
import cn.dev33.satoken.servlet.model.SaStorageForServlet;
import org.nutz.mvc.Mvcs;

import java.util.regex.Pattern;

/**
 * @author x1ngyu
 * @since 2024/10/10
 * <p>
 * Sa Token认证的上下文接口实现
 */

public class SaTokenContextForNutz implements cn.dev33.satoken.context.SaTokenContext {

    /**
     * 获取当前请求的Request对象
     *
     * @return SaRequest对象
     */
    @Override
    public SaRequest getRequest() {
        return new SaRequestForServlet(Mvcs.getReq());
    }

    /**
     * 获取当前请求的Response对象
     *
     * @return SaResponse对象
     */
    @Override
    public SaResponse getResponse() {
        return new SaResponseForServlet(Mvcs.getResp());
    }

    /**
     * 获取当前请求的 存储器 对象
     *
     * @return SaStorage对象
     */
    @Override
    public SaStorage getStorage() {
        return new SaStorageForServlet(Mvcs.getReq());
    }

    /**
     * 校验路由匹配符是否可以匹配路径
     */
    @Override
    public boolean matchPath(String pattern, String path) {
        String regex = pattern.replace("*", ".*");
        return Pattern.matches(regex, path);
    }
}
