package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.RoleDO;
import top.putongren.dxcblog.model.query.BaseQuery;

import java.util.List;

/**
 * @ClassName: RoleService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/27
 */
public interface RoleService {
    /**
     * 获取全部角色
     */
    List<RoleDO> listAllRole();
    /**
     * 分页获取全部角色
     */
    DataGridResult listPageRole(BaseQuery baseQuery);
    /**
     * 删除角色
     */
    DxcBlogResult delete(Long roleId);
    /**
     * 修改角色
     */
    DxcBlogResult update(RoleDO roleDO);
    /**
     * 添加角色
     */
    DxcBlogResult save(RoleDO roleDO);
}
