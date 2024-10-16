package space.sotis.starmaa.internal.interceptor;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;

/**
 * @author x1ngyu
 * @since 2024/10/15
 */

public class AdminPrivilegeInterceptor implements MethodInterceptor {
    @Override
    public void filter(InterceptorChain interceptorChain) throws Throwable {
//        try{
//            // todo
//        }
        interceptorChain.doChain();
    }
}
