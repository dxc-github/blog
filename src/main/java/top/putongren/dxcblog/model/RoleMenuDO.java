package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述: 角色菜单实体类
 * 〈〉
 * @Author: dxc
 * @Date: 2021/4/29 9:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuDO {
    private Long id;
    private Long roleId;
    private Long menuId;
}
