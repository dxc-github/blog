package top.putongren.dxcblog.dao;

import org.apache.ibatis.annotations.Param;
import top.putongren.dxcblog.model.ArticleDO;
import top.putongren.dxcblog.model.query.IndexQuery;
import top.putongren.dxcblog.model.vo.ArticleVO;

import java.util.List;

/**
 * @ClassName: ArticleDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface ArticleDao {
    int countAllArticle();
    //前台查询
    int countArticle();

    ArticleVO getLatestArticle();

    int countByCateId(Long cateId);

    int saveArticle(ArticleDO article);

    List<ArticleVO> listArticleByTitle(@Param("title") String title);

    List<ArticleVO> listSimilarsArticle();

    List<ArticleVO> listIndexArticle(IndexQuery query);

    int update(ArticleDO article);

    ArticleDO getArticleById(long id);

    int delete(long id);

    int updateViewsById(long id);

    int updateApproveCntById(long articleId);
}
