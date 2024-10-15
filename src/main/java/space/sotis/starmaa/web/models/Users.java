package space.sotis.starmaa.web.models;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;
import space.sotis.starmaa.internal.dao.StringArrayAdaptor;

import java.sql.Timestamp;

/**
 * @author x1ngyu
 * @since 2024/10/11
 * <p>
 * ORM
 */
@Data
@Table("users")
public class Users {
    @Id(auto = false)
    @Column("user_id")
    @ColDefine(type = ColType.INT)
    private int userId;

    @Column
    @ColDefine(type = ColType.TEXT)
    private transient String password;

    @Column
    @ColDefine(type = ColType.TEXT)
    private String email;

    /*
    todo 手机号合法性判断
    简易：数据库字段限长11
    完整：Hibernate Validator或类似方案，支持多种格式/Pattern
     */
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String phone;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String username;

    @Column("device_id")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String deviceId;

    @Column
    @ColDefine(type = ColType.TEXT)
    private String avatar;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String birthday;

    /*
    权限角色数组，默认值由数据库维护（["guest"]）
     */
    @Column
    @ColDefine(type = ColType.PSQL_ARRAY, insert = false, customType = "text[]", adaptor = StringArrayAdaptor.class)
    private String[] role;

    @Column("remain_days")
    @ColDefine(type = ColType.INT)
    private int remainDays;

    /*
    下两个字段由数据库维护。
     */
    @Column("created_at")
    @ColDefine(type = ColType.TIMESTAMP)
    @Readonly
    private Timestamp createdAt;

    @Column("updated_at")
    @ColDefine(type = ColType.TIMESTAMP)
    @Readonly
    private Timestamp updatedAt;
}
