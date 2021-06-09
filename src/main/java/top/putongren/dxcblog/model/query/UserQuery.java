package top.putongren.dxcblog.model.query;

import lombok.Data;

/**
 * @ClassName: UserQuery
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Data
public class UserQuery extends BaseQuery {
    private static final long serialVersionUID = 1L;

    private String nickname;
}
