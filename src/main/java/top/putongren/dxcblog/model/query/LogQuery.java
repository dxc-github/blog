package top.putongren.dxcblog.model.query;

import lombok.Data;

/**
 * @ClassName: LogQuery
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Data
public class LogQuery extends BaseQuery {
    private static final long serialVersionUID = 1L;
    // 用户名
    private String username;
    // 操作
    private String operation;
}
