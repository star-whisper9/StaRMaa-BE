package space.sotis.starmaa.web.models;

import lombok.Data;
import org.nutz.dao.entity.annotation.*;

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
    @Id
    private int id;

    @Name
    @Column("user_id")
    @ColDefine(type = ColType.INT)
    private int userId;

    @Column
    @ColDefine(type = ColType.TEXT)
    private String password;

    @Column
    @ColDefine(type = ColType.TEXT)
    private String email;

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

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String role;

    @Column("remain_days")
    @ColDefine(type = ColType.INT)
    private int remainDays;

    @Column("created_at")
    @ColDefine(type = ColType.TIMESTAMP)
    @Readonly
    private Timestamp createdAt;

    @Column("updated_at")
    @ColDefine(type = ColType.TIMESTAMP)
    @Readonly
    private Timestamp updatedAt;
}
