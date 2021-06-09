package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.dao.RoleDao;
import top.putongren.dxcblog.dao.RoleMenuDao;
import top.putongren.dxcblog.dao.UserRoleDao;
import top.putongren.dxcblog.model.CateDO;
import top.putongren.dxcblog.model.RoleDO;
import top.putongren.dxcblog.model.RoleMenuDO;
import top.putongren.dxcblog.model.query.BaseQuery;
import top.putongren.dxcblog.service.RoleService;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: RoleServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/27
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public List<RoleDO> listAllRole() {
        return roleDao.listRole();
    }

    @Override
    public DataGridResult listPageRole(BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPage(),baseQuery.getLimit());
        List<RoleDO> roleDOList = roleDao.listRole();
        //取记录总条数
        PageInfo<RoleDO> pageInfo = new PageInfo<>(roleDOList);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(roleDOList);
        result.setCount(total);
        return result;
    }

    @Override
    @Transactional
    public DxcBlogResult delete(Long roleId) {
        if(userRoleDao.countByRoleId(roleId) != 0){
            return DxcBlogResult.build(500,"角色已分配，请修改后再进行删除");
        }

        if(roleDao.remove(roleId) <= 0){
            return DxcBlogResult.build(500,"角色删除失败");
        }
        RoleMenuDO roleMenuDO = new RoleMenuDO();
        roleMenuDO.setRoleId(roleId);
        if(roleMenuDao.remove(roleMenuDO) <= 0){
            return DxcBlogResult.build(500,"菜单删除失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult update(RoleDO roleDO) {
        roleDO.setUpdateTime(new Date());
        int issucc = roleDao.update(roleDO);
        if(issucc<=0){
            return DxcBlogResult.build(500,"角色修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult save(RoleDO roleDO) {
        roleDO.setCreateTime(new Date());
        roleDO.setUpdateTime(new Date());
        int issucc = roleDao.save(roleDO);
        if(issucc<=0){
            return DxcBlogResult.build(500,"角色添加失败");
        }
        return DxcBlogResult.ok();
    }
}
