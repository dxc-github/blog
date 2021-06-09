package top.putongren.dxcblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.putongren.dxcblog.model.UserDO;

/**
 * @ClassName: UserVO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO extends UserDO {
    private static final long serialVersionUID = 1L;

    private Long roleId;
    private String roleName;

    public UserDO getUserDO(){
        UserDO userDO = new UserDO();
        userDO.setUsername(this.getUsername());
        userDO.setNickname(this.getNickname());
        userDO.setPassword(this.getPassword());
        userDO.setAvatar(this.getAvatar());
        userDO.setEmail(this.getEmail());
        userDO.setEnable(this.getEnable());
        userDO.setQqOpenId(this.getQqOpenId());
        userDO.setWxOpenId(this.getWxOpenId());
        userDO.setCreateTime(this.getCreateTime());
        userDO.setUpdateTime(this.getUpdateTime());
        return userDO;
    }
}
