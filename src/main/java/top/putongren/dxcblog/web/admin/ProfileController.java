package top.putongren.dxcblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.service.UploadService;
import top.putongren.dxcblog.service.UserService;
import top.putongren.dxcblog.web.BaseController;

import java.util.Date;

/**
 * @ClassName: ProfileController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/26
 */
@Controller
@RequestMapping("/management/profile")
public class ProfileController extends BaseController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("loginUser", getUser());
        return "management/profile";
    }

    @PostMapping("/edit")
    @ResponseBody
    public DxcBlogResult edit(String username, String nickname,
                              String password1, String password2, String avatar) {
        if (password1.equals(password2)) {
            UserDO user = new UserDO();
            user.setUsername(username);
            if (!StringUtils.isEmpty(password1)) {
                user.setPassword(DigestUtils.md5DigestAsHex(password1.getBytes()));
            }
            user.setNickname(nickname);
            user.setAvatar(avatar);
            user.setUpdateTime(new Date());
            return userService.updateByUsername(user);
        }
        return DxcBlogResult.build(500, "密码验证不一致，请检查！");
    }

    @PostMapping("/upload")
    @ResponseBody
    public DxcBlogResult upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        if (file != null) {
            return uploadService.upload(file);
        } else {
            return DxcBlogResult.build(500, "上传文件为空！");
        }
    }
}
