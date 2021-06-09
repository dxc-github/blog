package top.putongren.dxcblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: TaskVO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Long id;
    // 任务名
    private String jobName;
    // 任务描述
    private String description;
    // 任务分组
    private String jobGroup;
    // 任务执行时调用哪个类的方法 包名+类名
    private String beanClass;
    // cron表达式
    private String cronExpression;
    // 任务状态
    private String jobStatus;
}
