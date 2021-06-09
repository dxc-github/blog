package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: FileDO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String url;
    private Date post;
}
