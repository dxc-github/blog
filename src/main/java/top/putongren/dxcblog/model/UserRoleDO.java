package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述: 用户角色关联实体类
 * 〈〉
 * @Author: dxc
 * @Date: 2021/4/29 9:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDO {
    private Long id;
    private Long userId;
    private Long roleId;
}
