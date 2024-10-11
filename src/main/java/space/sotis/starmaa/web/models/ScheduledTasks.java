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
@Table("scheduled_tasks")
public class ScheduledTasks {
    @Id
    private int id;

    /**
     * 任务的重复运行日期组合（周一到周日）
     *
     * @see space.sotis.starmaa.util.WeekdayAlgorithm
     */
    @Column
    @ColDefine(type = ColType.INT)
    private int days;

    @Column
    @ColDefine(type = ColType.PSQL_ARRAY)
    private Timestamp[] times;

    @Column("next_run_at")
    @ColDefine(type = ColType.TIMESTAMP)
    private Timestamp nextRunAt;

    @Column("user_id")
    @ColDefine(type = ColType.INT)
    private int userId;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String task;

    @Column("updated_at")
    @ColDefine(type = ColType.TIMESTAMP)
    @Readonly
    private Timestamp createdAt;

    @Column("created_at")
    @ColDefine(type = ColType.TIMESTAMP)
    @Readonly
    private Timestamp updatedAt;
}
