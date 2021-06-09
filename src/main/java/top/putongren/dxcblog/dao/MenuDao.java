package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.MenuDO;

import java.util.List;

public interface MenuDao {
    int remove(Long menuId);

    int save(MenuDO menuDO);

    MenuDO get(Long menuId);

    int update(MenuDO menuDO);

    List<String> listUserPerms(Long userId);

    List<MenuDO> listMenu(String name);
}
