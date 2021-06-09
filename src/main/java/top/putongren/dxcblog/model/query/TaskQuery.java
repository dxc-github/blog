package top.putongren.dxcblog.model.query;

import lombok.Data;

/**
 * @ClassName: TaskQuery
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Data
public class TaskQuery extends BaseQuery {
    private static final long serialVersionUID = 1L;
    // 任务描述
    private String description;
}
