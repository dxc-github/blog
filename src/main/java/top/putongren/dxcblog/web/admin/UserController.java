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
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.model.query.UserQuery;
import top.putongren.dxcblog.model.vo.UserVO;
import top.putongren.dxcblog.service.RoleService;
import top.putongren.dxcblog.service.UserService;

/**
 * @ClassName: UserController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Controller
@RequestMapping("/management/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions("sys:user:index")
    @GetMapping
    public String index() {
        return "management/users";
    }

    @GetMapping("/toadd")
    public String toadd(Model model) {
        model.addAttribute("roleList",roleService.listAllRole());
        return "management/user_add";
    }

    @Log("用户查询")
    @GetMapping("/list")
    @ResponseBody
    public DataGridResult list(UserQuery userQuery) {
        //查找非管理员
        DataGridResult result = userService.listPageUser(userQuery);
        return result;
    }

    @Log("用户状态修改")
    @RequiresPermissions("sys:user:enable")
    @PostMapping("/edit/enable")
    @ResponseBody
    public DxcBlogResult editEnable(Long id, Boolean enable) {
        DxcBlogResult result = userService.updateEnableById(id, enable);
        return result;
    }

    @PostMapping("/save")
    @ResponseBody
    public DxcBlogResult saveUser(UserVO userVO) {
        return userService.addUser(userVO);
    }

    @PostMapping("/delete")
    @ResponseBody
    public DxcBlogResult deleteUser(Long id) {
        return userService.deleteUser(id);
    }

}
