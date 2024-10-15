package space.sotis.starmaa.internal.interceptor;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import space.sotis.starmaa.util.LoggerUtil;

/**
 * @author x1ngyu
 * @see space.sotis.starmaa.internal.interceptor.ControllerLoggingInterceptor
 * @since 2024/10/12
 * <p>
 * 服务层日志拦截器，在此层忽略抛出的异常，直接抛出到上层处理。
 */
@IocBean
public class ServiceLoggingInterceptor implements MethodInterceptor {
    private static final Logger logger = LoggerUtil.getLogger();

    @Override
    public void filter(InterceptorChain interceptorChain) throws Throwable {
        logger.info("调用方法: {}", interceptorChain.getCallingMethod().getName());
        try {
            interceptorChain.doChain();
        } finally {
            logger.info("调用方法: {} 结束", interceptorChain.getCallingMethod().getName());
        }
    }
}
