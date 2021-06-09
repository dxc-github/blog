package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.UserRoleDO;

public interface UserRoleDao {
    int deleteByUserId(Long id);

    int save(UserRoleDO userRoleDO);

    UserRoleDO get(Long id);

    int update(UserRoleDO userRoleDO);

    int countByRoleId(Long roleId);
}
