package space.sotis.starmaa;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import space.sotis.starmaa.util.RedisUtil;

/**
 * @author x1ngyu
 * @since 2024/10/10
 */

public class MainSetup implements Setup {
    @Override
    public void init(NutConfig nutConfig) {

    }

    @Override
    public void destroy(NutConfig nutConfig) {
        // 关闭Redis连接池
        RedisUtil.close();
    }
}
