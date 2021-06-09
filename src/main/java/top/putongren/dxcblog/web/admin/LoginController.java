package top.putongren.dxcblog.web.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.enums.ResultEnum;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.web.BaseController;
@Controller
public class LoginController extends BaseController {

    @GetMapping("/login")
    public String login(){
        UserDO user = getUser();
        if(user != null){
            return "redirect:management/index";
        }
        return "management/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public DxcBlogResult login(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            token.setRememberMe(rememberMe);
            subject.login(token);
        } catch (Exception e) {
            return DxcBlogResult.build(ResultEnum.UNKONW_ERROR.getCode(), e.getMessage());
        }
        return DxcBlogResult.ok();
    }
}
