package top.putongren.dxcblog.model.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: BaseQuery
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private int page;
    private int limit;
}
