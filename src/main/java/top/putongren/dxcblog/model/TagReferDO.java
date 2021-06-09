package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: TagReferDO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagReferDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long referId;
    private Long tagId;
    private Boolean isShow;
    private String type;
}
