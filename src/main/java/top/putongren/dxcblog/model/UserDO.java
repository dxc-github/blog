package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述: 用户实体类
 * 〈〉
 * @Author: dxc
 * @Date: 2021/4/29 9:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String avatar;
    private String email;
    private Boolean enable;
    private String qqOpenId;
    private String wxOpenId;
    private Date createTime;
    private Date updateTime;
}
