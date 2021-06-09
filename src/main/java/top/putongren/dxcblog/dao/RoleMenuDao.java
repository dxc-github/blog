package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.RoleMenuDO;

import java.util.List;

public interface RoleMenuDao {
    int remove(RoleMenuDO roleMenuDO);

    int save(RoleMenuDO roleMenuDO);

    RoleMenuDO get(Long id);

    int update(RoleMenuDO roleMenuDO);

    int countByMenuId(Long menuId);

    List<Long> selectByRoleId(Long roleId);

}
