package top.putongren.dxcblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.MenuDO;
import top.putongren.dxcblog.model.query.BaseQuery;
import top.putongren.dxcblog.model.query.MenuQuery;
import top.putongren.dxcblog.model.query.MenuTreeQuery;
import top.putongren.dxcblog.service.MenuService;
import top.putongren.dxcblog.web.BaseController;

/**
 * @ClassName: MenuController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/28
 */
@Controller
@RequestMapping("/management/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public String menus(){
        return "management/menus";
    }

    @GetMapping("/toadd")
    public String menuadd(Model model){
        model.addAttribute("menuList",menuService.listMenu());
        return "management/menu_add";
    }

    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listMenu(MenuQuery menuQuery){

        return menuService.listPageMenu(menuQuery);
    }

    @PostMapping("/save")
    @ResponseBody
    public DxcBlogResult addMenu(MenuDO menuDO){
        menuDO.setCreateUserId(getUserId());
        menuDO.setUpdateUserId(getUserId());
        return menuService.saveMenu(menuDO);
    }

    @PostMapping("/edit")
    @ResponseBody
    public DxcBlogResult editMenu(MenuDO menuDO){
        menuDO.setUpdateUserId(getUserId());
        return menuService.updateMenu(menuDO);
    }

    @PostMapping("/delete")
    @ResponseBody
    public DxcBlogResult deleteMenu(Long menuId){

        return menuService.deleteMenu(menuId);
    }

    @PostMapping("/tree/edit")
    @ResponseBody
    public DxcBlogResult menuTreeEdit(MenuTreeQuery menuTreeQuery){
        return menuService.menuTreeEdit(menuTreeQuery);
    }
}
