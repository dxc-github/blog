package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.CommentDO;
import top.putongren.dxcblog.model.query.CommentQuery;
import top.putongren.dxcblog.model.vo.CommentVO;

import java.util.List;

/**
 * @ClassName: CommentDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface CommentDao {
    int countAllComment();

    CommentVO getLatestComment();

    List<CommentVO> listCommentByArticleId(Long articleId);

    int insert(CommentDO comment);

    List<CommentVO> listComment(CommentQuery commentQuery);

    int updateEnableById(CommentDO commentDO);
}
