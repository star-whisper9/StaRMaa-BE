package space.sotis.starmaa.internal.interceptor;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import space.sotis.starmaa.util.LoggerUtil;

/**
 * @author x1ngyu
 * @since 2024/10/12
 */
@IocBean
public class DefaultLoggingInterceptor implements MethodInterceptor {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    public void filter(InterceptorChain interceptorChain) {
        logger.info("调用方法: {}", interceptorChain.getCallingMethod().getName());
        try {
            interceptorChain.doChain();
        } catch (Throwable e) {
            logger.error("调用方法: {} 出现异常: {}", interceptorChain.getCallingMethod().getName(), e.getMessage());
            if (logger.isDebugEnabled()) {
                logger.debug("完整异常: ", e);
            }
        } finally {
            logger.info("调用方法: {} 结束", interceptorChain.getCallingMethod().getName());
        }
    }
}
