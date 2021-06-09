package top.putongren.dxcblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.NKBlogResult;
import top.putongren.dxcblog.model.ArticleDO;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.model.query.ArticleQuery;
import top.putongren.dxcblog.service.ArticleService;
import top.putongren.dxcblog.service.CateService;
import top.putongren.dxcblog.service.TagReferService;
import top.putongren.dxcblog.service.UploadService;
import top.putongren.dxcblog.web.BaseController;

import java.util.Arrays;

/**
 * @ClassName: ArticleController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/21
 */
@Controller
@RequestMapping("/management/blog")
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CateService cateService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private TagReferService tagReferService;

    @RequiresPermissions("blog:blog:index")
    @GetMapping
    public String blog(Model model){
        model.addAttribute("cateList",cateService.listAllCate());
        return "management/blog";
    }

    @RequiresPermissions("blog:blogs:index")
    @GetMapping("/index")
    public String index() {
        return "management/blogs";
    }

    @Log("添加博客文章")
    @RequiresPermissions("blog:blog:add")
    @PostMapping("/add")
    @ResponseBody
    public DxcBlogResult add(ArticleDO article, String tagName) {
        UserDO user = getUser();
        article.setAuthorId(user.getId());
        return articleService.saveArticle(article, tagName);
    }

    @Log("查询博客文章")
    @PostMapping("/list")
    @ResponseBody
    public DataGridResult listArticle(ArticleQuery query) {
        DataGridResult result = articleService.listPageArticle(query);
        return result;
    }

    @Log("打赏状态修改")
    @RequiresPermissions("blog:blogs:appreciable")
    @PostMapping("/edit/appreciable/{id}")
    @ResponseBody
    public DxcBlogResult appreciable(@PathVariable("id") Long id, Boolean appreciable) {

        return articleService.updateAppreciableById(id, appreciable);
    }

    @Log("评论状态修改")
    @RequiresPermissions("blog:blogs:commented")
    @PostMapping("/edit/commented/{id}")
    @ResponseBody
    public DxcBlogResult commented(@PathVariable("id") Long id, Boolean commented) {

        return articleService.updateCommentedById(id, commented);
    }

    @Log("置顶状态修改")
    @RequiresPermissions("blog:blogs:top")
    @PostMapping("/edit/top/{id}")
    @ResponseBody
    public DxcBlogResult top(@PathVariable("id") Long id, Boolean top) {

        return articleService.updateTopById(id, top);
    }

    @RequiresPermissions("blog:blogs:editIndex")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("cateList", cateService.listAllCate());
        model.addAttribute("editArticle", articleService.getArticleById(id));
        String[] tagArray = tagReferService.listNameByArticleId(id).toArray(new String[]{});
        String tags = Arrays.toString(tagArray);
        model.addAttribute("articleTags", tags.substring(1, tags.length() - 1));
        return "management/blog_edit";
    }

    @Log("编辑文章提交")
    @RequiresPermissions("blog:blog:edit")
    @PostMapping("/doEdit")
    @ResponseBody
    public DxcBlogResult doEdit(ArticleDO article, String tagName) {
        UserDO user = getUser();
        article.setAuthorId(user.getId());
        return articleService.updateArticle(article, tagName);
    }

    @Log("删除文章")
    @RequiresPermissions("blog:blogs:delete")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public DxcBlogResult delete(@PathVariable("id") Long id) {
        return articleService.delete(id);
    }

    @Log("上传文章封面")
    @RequiresPermissions("blog:blog:cover")
    @PostMapping("/upload/cover")
    @ResponseBody
    public DxcBlogResult uploadCover(@RequestParam(value = "file", required = false) MultipartFile file) {
        if (file != null) {
            return uploadService.upload(file);
        } else {
            return DxcBlogResult.build(500, "上传文件为空！");
        }
    }

    @Log("上传文章图片")
    @RequiresPermissions("blog:blog:upload")
    @PostMapping("/upload")
    @ResponseBody
    public NKBlogResult upload(@RequestParam(value = "uploadFile", required = false) MultipartFile file) {
        if (file != null) {
            return uploadService.uploadNK(file);
        } else {
            return NKBlogResult.build(001, "上传文件为空！");
        }
    }
}
