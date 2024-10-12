package space.sotis.starmaa.util;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author x1ngyu
 * @since 2024/10/12
 * <p>
 * 日志获取器
 */

public class LoggerUtil {
    @Getter
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    private LoggerUtil() {
    }
}
