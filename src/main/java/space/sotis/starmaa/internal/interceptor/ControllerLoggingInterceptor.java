package space.sotis.starmaa.internal.interceptor;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import space.sotis.starmaa.util.LoggerUtil;

/**
 * @author x1ngyu
 * @see space.sotis.starmaa.internal.interceptor.ServiceLoggingInterceptor
 * @since 2024/10/15
 * <p>
 * 控制器类的日志拦截器，在此层记录异常的错误日志（便于完整的堆栈跟踪），<code>@Fail</code>注解需要入口方法抛出异常才能正确处理错误视图返回，因此在日志记录完成后重新抛出异常。
 */
@IocBean
public class ControllerLoggingInterceptor implements MethodInterceptor {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    public void filter(InterceptorChain interceptorChain) throws Throwable {
        logger.info("进入端点方法: {}", interceptorChain.getCallingMethod().getName());
        try {
            interceptorChain.doChain();
        } catch (Throwable e) {
            logger.error("调用方法: {} 出现异常: {}", interceptorChain.getCallingMethod().getName(), e.getMessage());
            if (logger.isDebugEnabled()) {
                logger.debug("完整异常: ", e);
            }
            throw e;
        } finally {
            logger.info("调用端点方法: {} 结束", interceptorChain.getCallingMethod().getName());
        }
    }
}
