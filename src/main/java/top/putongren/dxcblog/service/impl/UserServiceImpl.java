package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.utils.IDUtils;
import top.putongren.dxcblog.dao.RoleDao;
import top.putongren.dxcblog.dao.UserDao;
import top.putongren.dxcblog.dao.UserRoleDao;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.model.UserRoleDO;
import top.putongren.dxcblog.model.query.UserQuery;
import top.putongren.dxcblog.model.vo.UserVO;
import top.putongren.dxcblog.service.UserService;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public DataGridResult listPageUser(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPage(),userQuery.getLimit());
        List<UserVO> userDOList = userDao.listUserByNickname(userQuery.getNickname());
        PageInfo<UserVO> pageInfo = new PageInfo<>(userDOList);
        Long total = pageInfo.getTotal();

        DataGridResult result = new DataGridResult();
        result.setCount(total);
        result.setData(userDOList);
        return result;
    }

    @Override
    public DxcBlogResult updateEnableById(Long id, Boolean enable) {
        UserDO user = new UserDO();
        user.setId(id);
        user.setEnable(enable);
        user.setUpdateTime(new Date());
        int issucc = userDao.update(user);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult updateByUsername(UserDO user) {
        int issucc = userDao.updateByUsername(user);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改资料失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public String getNicknameById(long id) {
        return null;
    }

    @Override
    public UserDO getUserById(long id) {
        return null;
    }

    @Override
    @Transactional
    public DxcBlogResult addUser(UserVO userVO) {
        UserDO user = userVO.getUserDO();
        String password = DigestUtils.md5DigestAsHex(userVO.getPassword().getBytes());
        user.setPassword(password);
        user.setId(IDUtils.genId());
        user.setEnable(true);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int issucc = userDao.save(user);
        if(issucc<=0){
            return DxcBlogResult.build(500,"用户添加失败");
        }
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setRoleId(userVO.getRoleId());
        userRoleDO.setUserId(user.getId());
        int issucc2 =userRoleDao.save(userRoleDO);
        if(issucc2<=0){
            return DxcBlogResult.build(500,"角色添加失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult deleteUser(Long id) {
        if(userDao.delete(id) <= 0){
            return DxcBlogResult.build(500,"用户删除失败");
        }
        int issucc = userRoleDao.deleteByUserId(id);
        if(issucc<=0){
            return DxcBlogResult.build(500,"角色关联删除失败");
        }
        return DxcBlogResult.ok();
    }
}
