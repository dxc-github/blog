package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.utils.FilterHtml;
import top.putongren.dxcblog.common.utils.IDUtils;
import top.putongren.dxcblog.dao.ArticleDao;
import top.putongren.dxcblog.dao.TagDao;
import top.putongren.dxcblog.dao.TagReferDao;
import top.putongren.dxcblog.model.ArticleDO;
import top.putongren.dxcblog.model.TagDO;
import top.putongren.dxcblog.model.TagReferDO;
import top.putongren.dxcblog.model.query.ArticleQuery;
import top.putongren.dxcblog.model.vo.ArticleVO;
import top.putongren.dxcblog.service.ArticleService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ArticleServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private TagReferDao tagReferDao;


    @Override
    public int countAllArticle() {
        return articleDao.countAllArticle();
    }

    @Override
    public ArticleVO getLatestArticle() {
        return articleDao.getLatestArticle();
    }

    @Override
    @Transactional
    public DxcBlogResult saveArticle(ArticleDO article, String tagName) {
        article.setId(IDUtils.genId());
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        //浏览数
        article.setViews(0);
        //点赞数
        article.setApproveCnt(0);
        article.setAppreciable(article.getAppreciable() == null ? false : true );
        article.setCommented(article.getCommented() == null ? false : true );
        article.setTop(false);
        getSumByFilterContent(article);
        articleDao.saveArticle(article);
        //插入标签
        if (!StringUtils.isEmpty(tagName)) {
            //标签处理
            String[] tagNameArray = tagName.split(",");
            TagDO tag = new TagDO();
            TagReferDO tagRefer = new TagReferDO();
            for (String name : Arrays.asList(tagNameArray)) {
                if (tagDao.countByName(name) == 0) {
                    tag.setId(IDUtils.genId());
                    tag.setName(name);
                    tagDao.saveTag(tag);
                } else {
                    tag = tagDao.getTagByName(name);
                }
                tagRefer.setId(IDUtils.genId());
                tagRefer.setReferId(article.getId());
                tagRefer.setTagId(tag.getId());
                tagRefer.setIsShow(true);
                tagRefer.setType("2");
                tagReferDao.saveTagRefer(tagRefer);
            }
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult saveSimpleArticle(ArticleDO article) {
        //设置文章信息
        article.setId(IDUtils.genId());
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        //浏览数
        article.setViews(0);
        //点赞数
        article.setApproveCnt(0);
        //设置是否开启赞赏
        article.setAppreciable(article.getAppreciable() == null ? false : true );
        //设置是否开启评论
        article.setCommented(article.getCommented() == null ? false : true );
        //设置是否置顶
        article.setTop(false);
        //是否发布
        article.setDraft(false);
        getSumByFilterContent(article);
        int issucc = articleDao.saveArticle(article);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DataGridResult listPageArticle(ArticleQuery articleQuery) {
        PageHelper.startPage(articleQuery.getPage(), articleQuery.getLimit());
        List<ArticleVO> list = articleDao.listArticleByTitle(articleQuery.getTitle());

        //取记录总条数
        PageInfo<ArticleVO> pageInfo = new PageInfo<ArticleVO>(list);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(list);
        result.setCount(total);
        return result;
    }

    @Override
    public DxcBlogResult updateAppreciableById(Long id, Boolean appreciable) {
        ArticleDO article = new ArticleDO();
        article.setId(id);
        article.setAppreciable(appreciable);
        article.setUpdateTime(new Date());
        int issucc = articleDao.update(article);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult updateCommentedById(Long id, Boolean commented) {
        ArticleDO article = new ArticleDO();
        article.setId(id);
        article.setCommented(commented);
        article.setUpdateTime(new Date());
        int issucc = articleDao.update(article);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult updateTopById(Long id, Boolean top) {
        ArticleDO article = new ArticleDO();
        article.setId(id);
        article.setTop(top);
        article.setUpdateTime(new Date());
        int issucc = articleDao.update(article);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public ArticleDO getArticleById(long id) {
        return articleDao.getArticleById(id);
    }

    @Override
    @Transactional
    public DxcBlogResult updateArticle(ArticleDO article, String tagName) {
        article.setUpdateTime(new Date());
        getSumByFilterContent(article);
        articleDao.update(article);
        tagReferDao.deleteByReferId(article.getId());
        String[] tagNameArray = tagName.split(",");
        TagDO tag = new TagDO();
        TagReferDO tagRefer = new TagReferDO();
        for (String name : Arrays.asList(tagNameArray)) {
            if (tagDao.countByName(name) == 0) {
                tag.setId(IDUtils.genId());
                tag.setName(name);
                tagDao.saveTag(tag);
            } else {
                tag = tagDao.getTagByName(name);
            }
            tagRefer.setId(IDUtils.genId());
            tagRefer.setReferId(article.getId());
            tagRefer.setTagId(tag.getId());
            tagRefer.setIsShow(true);
            tagRefer.setType("2");
            tagReferDao.saveTagRefer(tagRefer);
        }
        return DxcBlogResult.ok();
    }

    @Override
    @Transactional
    public DxcBlogResult delete(Long id) {
        tagReferDao.deleteByReferId(id);
        int issucc = articleDao.delete(id);
        if(issucc <= 0){
            return DxcBlogResult.build(500,"删除失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public int updateViewsById(long id) {
        return 0;
    }

    @Override
    public DxcBlogResult updateApproveCntById(long id) {
        return null;
    }

    private void getSumByFilterContent(ArticleDO article) {
        String clearContent = FilterHtml.filterHtml(article.getContent().trim());
        clearContent = StringUtils.trimAllWhitespace(clearContent);
        if (org.apache.commons.lang3.StringUtils.isEmpty(article.getSummary())) {
            String summary = clearContent.substring(0, clearContent.length() < 243 ? clearContent.length() : 243);
            article.setSummary(summary);
        }
        article.setTextContent(clearContent);
    }
}
