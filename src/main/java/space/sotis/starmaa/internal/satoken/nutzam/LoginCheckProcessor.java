package space.sotis.starmaa.internal.satoken.nutzam;

import cn.dev33.satoken.stp.StpUtil;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.impl.processor.AbstractProcessor;

/**
 * @author x1ngyu
 * @since 2024/10/12
 * <p>
 * 登录验证处理器
 */

public class LoginCheckProcessor extends AbstractProcessor {
    @Override
    public void process(ActionContext actionContext) throws Throwable {
        if (!StpUtil.isLogin()) {
            actionContext.getResponse().sendError(401);
            return;
        }
        doNext(actionContext);
    }
}
