package top.putongren.dxcblog.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuTreeVO {
    private Long id;
    private String title;
    private String field;
    private Boolean checked;
    private String href;
    private Boolean spread;
    private Boolean disabled;
    private List<MenuTreeVO> children;


    public MenuTreeVO getMenUTreeVO(MenuVO menuVO,List<Long> menuIds){
        MenuTreeVO resultMenuTreeVO = new MenuTreeVO();
        resultMenuTreeVO.setId(menuVO.getMenuId());
        resultMenuTreeVO.setTitle(menuVO.getName());
        resultMenuTreeVO.setField(resultMenuTreeVO.getTitle() + resultMenuTreeVO.getId());
        resultMenuTreeVO.setChecked(false);
        resultMenuTreeVO.setHref(menuVO.getUrl());
        resultMenuTreeVO.setSpread(true);
        resultMenuTreeVO.setDisabled(false);

        if(menuVO.getChildren() != null && menuVO.getChildren().size() > 0){
            List<MenuTreeVO> tempChildren = new ArrayList<>();
            for (MenuVO menu : menuVO.getChildren()){
                tempChildren.add(this.getMenUTreeVO(menu,menuIds));
            }
            resultMenuTreeVO.setChildren(tempChildren);
        }else {
            if(menuIds.indexOf(resultMenuTreeVO.getId()) != -1){
                resultMenuTreeVO.setChecked(true);
            }
        }
        return resultMenuTreeVO;
    }

}
