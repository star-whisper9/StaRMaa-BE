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
@Table("task_history")
public class TaskHistory {
    @Name
    @Column("task_id")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String taskId;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String task;

    @Column("user_id")
    @ColDefine(type = ColType.INT)
    private int userId;

    @Column("created_at")
    @ColDefine(type = ColType.TIMESTAMP)
    private Timestamp createdAt;

    @Column("updated_at")
    @ColDefine(type = ColType.TIMESTAMP)
    private Timestamp updatedAt;
}
