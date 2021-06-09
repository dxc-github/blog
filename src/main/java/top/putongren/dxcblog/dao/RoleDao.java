package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.RoleDO;

import java.util.List;

public interface RoleDao {
    int remove(Long roleId);

    int save(RoleDO roleDO);

    RoleDO get(Long roleId);

    int update(RoleDO roleDO);

    List<RoleDO> listRole();
}
