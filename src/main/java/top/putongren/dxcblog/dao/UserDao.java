package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.model.vo.UserVO;

import java.util.List;

public interface UserDao {
    UserDO getUserByName(String username);

    UserDO getUserById(long id);

    List<UserVO> listUserByNickname(String nickname);

    int update(UserDO user);

    int updateByUsername(UserDO user);

    UserDO getUserByQqOpenId(String qqOpenId);

    UserDO getUserByWxOpenId(String wxOpenId);

    int save(UserDO user);

    int updateWxUser(UserDO user);

    String getNicknameById(long id);

    int delete(Long id);
}
