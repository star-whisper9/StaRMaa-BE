package space.sotis.starmaa.di;

import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author x1ngyu
 * @since 2024/10/10
 * <p>
 * 日志工厂封装
 */
@IocBean
public class Log {
    private static final ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();

    public Logger getLogger(Class<?> clazz) {
        return loggerFactory.getLogger(clazz.getName());
    }
}
