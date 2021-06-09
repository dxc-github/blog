package top.putongren.dxcblog.model.query;

import lombok.Data;

import java.util.List;

@Data
public class MenuTreeQuery {
    private Long roleId;
    private List<Long> nodeIds;
}
