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
@Table("task_reports")
public class TaskReports {
    private enum Status {
        HOLDING,
        SUCCESS,
        FAILED
    }

    @Name
    @Column("task_id")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String taskId;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String task;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 16)
    private Status status;

    @Column
    @ColDefine(type = ColType.TEXT)
    private String payload;

    @Column("user_id")
    @ColDefine(type = ColType.INT)
    private int userId;

    @Column("device_id")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String deviceId;

    @Column("created_at")
    @ColDefine(type = ColType.TIMESTAMP)
    @Readonly
    private Timestamp createdAt;

    @Column("updated_at")
    @ColDefine(type = ColType.TIMESTAMP)
    @Readonly
    private Timestamp updatedAt;
}
