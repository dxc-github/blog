package top.putongren.dxcblog.model.query;

import lombok.Data;

/**
 * @ClassName: ArticleQuery
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
public class ArticleQuery extends BaseQuery {
    private static final long serialVersionUID = 1L;

    private String title;
}
