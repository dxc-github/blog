package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 功能描述: 菜单实体类
 * 〈〉
 * @Author: dxc
 * @Date: 2021/4/29 9:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDO {
    private Long menuId;
    private Long parentId;
    private String name;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer orderNum;
    private Long createUserId;
    private Date createTime;
    private Long updateUserId;
    private Date updateTime;
}
