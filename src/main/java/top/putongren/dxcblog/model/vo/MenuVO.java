package top.putongren.dxcblog.model.vo;

import lombok.Data;
import top.putongren.dxcblog.model.MenuDO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MenuVO extends MenuDO {
    private List<MenuVO> children;

    public MenuVO getMenuVO(MenuDO menuDO){
        MenuVO menuVO = new MenuVO();
        menuVO.setMenuId(menuDO.getMenuId());
        menuVO.setParentId(menuDO.getParentId());
        menuVO.setName(menuDO.getName());
        menuVO.setUrl(menuDO.getUrl());
        menuVO.setPerms(menuDO.getPerms());
        menuVO.setType(menuDO.getType());
        menuVO.setIcon(menuDO.getIcon());
        menuVO.setOrderNum(menuDO.getOrderNum());
        menuVO.setCreateUserId(menuDO.getCreateUserId());
        menuVO.setCreateTime(menuDO.getCreateTime());
        menuVO.setUpdateUserId(menuDO.getUpdateUserId());
        menuVO.setUpdateTime(menuDO.getUpdateTime());
        return menuVO;
    }

    public List<MenuVO> getTree(List<MenuDO> menuDOS){
        List<MenuVO> menuVOs1 = new ArrayList<>();
        List<MenuVO> menuVOs2 = new ArrayList<>();
        List<MenuVO> menuVOs3 = new ArrayList<>();

        for (MenuDO menuDO : menuDOS){
            switch (menuDO.getType()){
                case 0:
                    menuVOs1.add(getMenuVO(menuDO));
                    break;
                case 1:
                    menuVOs2.add(getMenuVO(menuDO));
                    break;
                default:
                    menuVOs3.add(getMenuVO(menuDO));
                    break;
            }
        }
        List<MenuVO> resultMenuVOs = new ArrayList<>();
        for(MenuVO menuVO1 : menuVOs1){
            List<MenuVO> temp1 = new ArrayList<>();
            for (MenuVO menuVO2 : menuVOs2){
                List<MenuVO> temp2 = new ArrayList<>();
                if(menuVO1.getMenuId() == menuVO2.getParentId()){
                    temp1.add(menuVO2);
                }
                for (MenuVO menuVO3 : menuVOs3){
                    if(menuVO2.getMenuId() == menuVO3.getParentId()){
                        temp2.add(menuVO3);
                    }
                }
                menuVO2.setChildren(temp2);
            }
            menuVO1.setChildren(temp1);
            resultMenuVOs.add(menuVO1);
        }
        return resultMenuVOs;
    }
}
