package top.putongren.dxcblog.model.query;

import lombok.Data;

/**
 * @ClassName: CommentQuery
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
public class CommentQuery extends BaseQuery {
    private static final long serialVersionUID = 1L;
    private Long articleId;
    private String articleTitle;
    private String nickname;
    private String content;
}
