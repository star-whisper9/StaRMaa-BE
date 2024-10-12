package space.sotis.starmaa;

import org.nutz.mvc.annotation.ChainBy;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;

/**
 * @author x1ngyu
 * @since 2024/10/10
 */
@Modules
@IocBy(args = {
        "*js", "ioc/",
        "*anno", "space.sotis.starmaa"
})
@SetupBy(MainSetup.class)
@ChainBy(args = "chain.jsonc")
public class MainModule {
}
