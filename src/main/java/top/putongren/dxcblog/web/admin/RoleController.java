package top.putongren.dxcblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.utils.JSONUtils;
import top.putongren.dxcblog.model.CateDO;
import top.putongren.dxcblog.model.RoleDO;
import top.putongren.dxcblog.model.query.BaseQuery;
import top.putongren.dxcblog.model.vo.MenuTreeVO;
import top.putongren.dxcblog.model.vo.MenuVO;
import top.putongren.dxcblog.service.MenuService;
import top.putongren.dxcblog.service.RoleService;
import top.putongren.dxcblog.web.BaseController;

import java.util.List;

/**
 * @ClassName: RoleController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/27
 */
@Controller
@RequestMapping("/management/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @GetMapping
    public String index() {
        return "management/roles";
    }

    @GetMapping("/tree/{roleId}")
    public String toMenuTree(Model model,@PathVariable Long roleId) {
        List<MenuTreeVO> tree = menuService.listTree(roleId);
        model.addAttribute("trees", JSONUtils.objectToJson(tree));
        model.addAttribute("roleId", roleId);
//        model.addAttribute("trees", tree);
        return "management/menutree";
    }

    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listCate(BaseQuery baseQuery) {
        DataGridResult result = roleService.listPageRole(baseQuery);
        return result;
    }

    @PostMapping("/add")
    @ResponseBody
    public DxcBlogResult add(RoleDO roleDO) {

        return roleService.save(roleDO);
    }

    @PostMapping("/delete")
    @ResponseBody
    public DxcBlogResult delete(long id) {

        return roleService.delete(id);
    }

    @PostMapping("/edit")
    @ResponseBody
    public DxcBlogResult edit(RoleDO roleDO) {

        return roleService.update(roleDO);
    }
}
