package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.MenuDO;
import top.putongren.dxcblog.model.query.BaseQuery;
import top.putongren.dxcblog.model.query.MenuQuery;
import top.putongren.dxcblog.model.query.MenuTreeQuery;
import top.putongren.dxcblog.model.vo.MenuTreeVO;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: MenuService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/26
 */
public interface MenuService {
    Set<String> listPerms(Long userId);

    DataGridResult listPageMenu(MenuQuery menuQuery);

    DxcBlogResult updateMenu(MenuDO menuDO);

    DxcBlogResult saveMenu(MenuDO menuDO);

    DxcBlogResult deleteMenu(Long menuId);

    List<MenuDO> listMenu();

    List<MenuTreeVO> listTree(Long roleId);

    DxcBlogResult menuTreeEdit(MenuTreeQuery menuTreeQuery);
}
