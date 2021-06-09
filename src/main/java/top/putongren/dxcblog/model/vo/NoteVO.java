package top.putongren.dxcblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: NoteVO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String content;
    private Date createTime;
}
