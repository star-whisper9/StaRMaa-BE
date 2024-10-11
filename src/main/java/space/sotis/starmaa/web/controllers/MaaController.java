package space.sotis.starmaa.web.controllers;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;

import java.util.Map;

/**
 * @author x1ngyu
 * @since 2024/10/11
 * <p>
 * Maa轮询的两个端点
 * <p>
 * todo
 */
@IocBean
@At("/api/maa")
public class MaaController {
    @Inject
    private Dao dao;

    @At("/get")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public String getTasks(@Param("..") String maaUserJson) {
        return null;
    }

    @At("/report")
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    @Ok("json")
    public Map<String, String> report(@Param("..") String reportJson) {
        return null;
    }
}
