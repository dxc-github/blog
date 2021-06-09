package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.CommentDO;
import top.putongren.dxcblog.model.query.CommentQuery;
import top.putongren.dxcblog.model.vo.CommentVO;

/**
 * @ClassName: CommentService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface CommentService {
    /**
     * 获取总的评论数
     */
    int countAllComment();
    /**
     * 获取最新的评论
     */
    CommentVO getLatestComment();
    /**
     * 评论查询
     */
    DataGridResult listComment(CommentQuery query);
    /**
     * 评论插入
     */
    DxcBlogResult  insert(CommentDO comment);
    /**
     * 评论状态修改
     */
    DxcBlogResult  updateEnableById(CommentDO comment);
}
