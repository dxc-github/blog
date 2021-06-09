package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 功能描述: 角色实体类
 * 〈〉
 * @Author: dxc
 * @Date: 2021/4/29 9:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDO {
    private Long roleId;
    private String roleName;
    private String roleCode;
    private String remark;
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
}
