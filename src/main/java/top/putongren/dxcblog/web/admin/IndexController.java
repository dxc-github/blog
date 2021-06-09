package top.putongren.dxcblog.web.admin;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.Constant;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.ArticleDO;
import top.putongren.dxcblog.model.NoteDO;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.service.ArticleService;
import top.putongren.dxcblog.service.CateService;
import top.putongren.dxcblog.service.CommentService;
import top.putongren.dxcblog.service.NoteService;
import top.putongren.dxcblog.web.BaseController;

/**
 * @ClassName: IndexController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/19
 */
@Controller
@RequestMapping("/management")
public class IndexController extends BaseController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CateService cateService;

    @RequiresPermissions("blog:manage:index")
    @GetMapping("/index")
    public String index(Model model){
        UserDO user = getUser();
        model.addAttribute("avatar", user.getAvatar());
        model.addAttribute("nickname", user.getNickname());
        return "management/index";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("articleCnt", articleService.countAllArticle());
        model.addAttribute("noteCnt", noteService.countAllNote());
        model.addAttribute("commentCnt", commentService.countAllComment());
        model.addAttribute("latestArticle", articleService.getLatestArticle());
        model.addAttribute("latestNote", noteService.getLatestNote());
        model.addAttribute("latestComment", commentService.getLatestComment());
        model.addAttribute("cateList", cateService.listAllCate());
        return "management/home";
    }

    @Log("首页添加博客文章")
    @RequiresPermissions("blog:blog:add")
    @PostMapping("/simple/add/article")
    @ResponseBody
    public DxcBlogResult simplePostArticle(ArticleDO article){
        if(article.getContent().length() > 300){
            return DxcBlogResult.build(500,"草稿字数不宜过多");
        }
        UserDO user = getUser();
        article.setAuthorId(user.getId());
        return articleService.saveSimpleArticle(article);
    }

    @Log("首页添加随笔")
    @RequiresPermissions("blog:note:add")
    @PostMapping("/simple/add/note")
    @ResponseBody
    public DxcBlogResult simplePostNote(NoteDO note){
        if(note.getContent().length() > 300){
            return DxcBlogResult.build(500,"笔记字数不宜过多");
        }
        return noteService.saveNote(note,null);
    }

    @GetMapping("/logout")
    public String logout(String from) {
        logout();
        if (StringUtils.isEmpty(from)) {
            return "redirect:/";
        } else {
            return "redirect:" + Constant.MANAGEMENT_INDEX;
        }
    }

}
