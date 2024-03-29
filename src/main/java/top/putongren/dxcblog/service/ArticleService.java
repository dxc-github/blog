package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.ArticleDO;
import top.putongren.dxcblog.model.query.ArticleQuery;
import top.putongren.dxcblog.model.vo.ArticleVO;

/**
 * @ClassName: ArticleService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface ArticleService {
    /**
     * 获取总的文章数
     */
    int countAllArticle();
    /**
     * 获取最新文章
     */
    ArticleVO getLatestArticle();
    /**
     * 创建文章
     */
    DxcBlogResult saveArticle(ArticleDO article, String tagName);
    /**
     * 创建草稿，首页用
     */
    DxcBlogResult saveSimpleArticle(ArticleDO article);
    /**
     * 分页查询文章
     */
    DataGridResult listPageArticle(ArticleQuery articleQuery);
    /**
     * 修改打赏状态
     */
    DxcBlogResult updateAppreciableById(Long id, Boolean appreciable);
    /**
     * 修改评论状态
     */
    DxcBlogResult updateCommentedById(Long id, Boolean commented);
    /**
     * 修改置顶状态
     */
    DxcBlogResult updateTopById(Long id, Boolean top);
    /**
     * 根据id获取文章
     */
    ArticleDO getArticleById(long id);
    /**
     * 更新文章
     */
    DxcBlogResult updateArticle(ArticleDO article, String tagName);
    /**
     * 删除文章
     */
    DxcBlogResult delete(Long id);
    /**
     * 更新文章浏览次数
     */
    int updateViewsById(long id);

    DxcBlogResult updateApproveCntById(long id);
}
