package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.model.query.UserQuery;
import top.putongren.dxcblog.model.vo.UserVO;

public interface UserService {

    DataGridResult listPageUser(UserQuery userQuery);

    DxcBlogResult updateEnableById(Long id, Boolean enable);

    DxcBlogResult updateByUsername(UserDO user);

    String getNicknameById(long id);

    UserDO getUserById(long id);

    DxcBlogResult addUser(UserVO userVO);

    DxcBlogResult deleteUser(Long id);
}
