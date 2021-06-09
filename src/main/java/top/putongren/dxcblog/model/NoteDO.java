package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: NoteDO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String textContent;
    private String content;
    private Boolean top;
    private Boolean isShow;
    private Date createTime;
    private Date updateTime;
}
