package top.putongren.dxcblog.model.query;

import lombok.Data;

/**
 * @ClassName: TagQuery
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
public class TagQuery extends BaseQuery {
    private static final long serialVersionUID = 1L;

    private String name;
}
