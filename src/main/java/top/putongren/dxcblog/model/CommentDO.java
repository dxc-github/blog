package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import static java.lang.Boolean.TRUE;

/**
 * @ClassName: CommentDO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long articleId;
    private String content;
    private String ipAddr;
    private String ipCnAddr;
    private String parentId;
    private Boolean enable;
    private Date createTime;
}
