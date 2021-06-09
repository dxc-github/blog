package top.putongren.dxcblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.AboutDO;
import top.putongren.dxcblog.service.AboutService;

/**
 * @ClassName: AboutController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Controller
@RequestMapping("/management/about")
public class AboutController {
    @Autowired
    private AboutService aboutService;

    @RequiresPermissions("blog:about:index")
    @GetMapping
    public String about(Model model) {
        model.addAttribute("me", aboutService.getAboutByTab("about_me"));
        model.addAttribute("website", aboutService.getAboutByTab("about_website"));
        model.addAttribute("blog", aboutService.getAboutByTab("about_blog"));
        return "management/about";
    }

    @GetMapping("/blog")
    public String index() {

        return "management/aboutblog";
    }

    @Log("修改关于内容")
    @RequiresPermissions("blog:about:update")
    @PostMapping("/update")
    @ResponseBody
    public DxcBlogResult update(AboutDO about) {

        return aboutService.updateByTab(about);
    }
}
