package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.dao.MenuDao;
import top.putongren.dxcblog.dao.RoleMenuDao;
import top.putongren.dxcblog.model.MenuDO;
import top.putongren.dxcblog.model.RoleMenuDO;
import top.putongren.dxcblog.model.query.BaseQuery;
import top.putongren.dxcblog.model.query.MenuQuery;
import top.putongren.dxcblog.model.query.MenuTreeQuery;
import top.putongren.dxcblog.model.vo.MenuTreeVO;
import top.putongren.dxcblog.model.vo.MenuVO;
import top.putongren.dxcblog.service.MenuService;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName: MenuServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/26
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public Set<String> listPerms(Long userId) {
        List<String> perms = menuDao.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public DataGridResult listPageMenu(MenuQuery menuQuery) {
        PageHelper.startPage(menuQuery.getPage(),menuQuery.getLimit());
        List<MenuDO> menuDOList = menuDao.listMenu(menuQuery.getName());
        PageInfo<MenuDO> pageInfo = new PageInfo<>(menuDOList);
        Long total = pageInfo.getTotal();
        DataGridResult result = new DataGridResult();
        result.setCount(total);
        result.setData(menuDOList);
        return result;
    }

    @Override
    public DxcBlogResult updateMenu(MenuDO menuDO) {
        int issucc = menuDao.update(menuDO);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult saveMenu(MenuDO menuDO) {
        menuDO.setCreateTime(new Date());
        menuDO.setUpdateTime(new Date());
        int issucc = menuDao.save(menuDO);
        if(issucc<=0){
            return DxcBlogResult.build(500,"保存失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult deleteMenu(Long menuId) {
        if(roleMenuDao.countByMenuId(menuId) != 0){
            return DxcBlogResult.build(500,"菜单已分配角色，修改后再进行删除");
        }

        if(menuDao.remove(menuId) <= 0){
            return DxcBlogResult.build(500,"菜单删除失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public List<MenuDO> listMenu() {
        return menuDao.listMenu(null);
    }

    @Override
    public List<MenuTreeVO> listTree(Long roleId) {
        List<MenuDO> menuDOList = menuDao.listMenu(null);
        List<MenuTreeVO> menuTreeVOList = new ArrayList<>();
        MenuVO menuVO = new MenuVO();
        List<MenuVO> menuVOList = menuVO.getTree(menuDOList);
        MenuTreeVO menuTreeVO = new MenuTreeVO();
        List<Long> menuIds = roleMenuDao.selectByRoleId(roleId);
        for(MenuVO menuVO1 : menuVOList){
            menuTreeVOList.add(menuTreeVO.getMenUTreeVO(menuVO1,menuIds));
        }
        return menuTreeVOList;
    }

    @Override
    @Transactional
    public DxcBlogResult menuTreeEdit(MenuTreeQuery menuTreeQuery) {
        List<Long> menuIdList = roleMenuDao.selectByRoleId(menuTreeQuery.getRoleId());
        List<Long> nodeIdList = menuTreeQuery.getNodeIds();
        RoleMenuDO roleMenuDO = new RoleMenuDO();;
        roleMenuDO.setRoleId(menuTreeQuery.getRoleId());
        if(menuIdList == null || menuIdList.size() == 0){
            for (Long menuId:nodeIdList){
                roleMenuDO.setMenuId(menuId);
                roleMenuDao.save(roleMenuDO);
            }
            return DxcBlogResult.ok();
        }
        List<Long> deleteMenus = menuIdList.stream().filter(item -> !nodeIdList.contains(item)).collect(toList());
        List<Long> saveMenus = nodeIdList.stream().filter(item -> !menuIdList.contains(item)).collect(toList());
        for (Long menuId:deleteMenus){
            roleMenuDO.setMenuId(menuId);
            roleMenuDao.remove(roleMenuDO);
        }
        for (Long menuId:saveMenus){
            roleMenuDO.setMenuId(menuId);
            roleMenuDao.save(roleMenuDO);
        }
        return DxcBlogResult.ok();
    }
}
