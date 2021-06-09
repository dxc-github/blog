package top.putongren.dxcblog.model.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: IndexQuery
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
public class IndexQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String textContent;
    private Long cateId;
    private String tag;
}
