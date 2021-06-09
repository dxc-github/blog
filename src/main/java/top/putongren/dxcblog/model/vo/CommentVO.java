package top.putongren.dxcblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.putongren.dxcblog.model.CommentDO;

/**
 * @ClassName: CommentVO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO extends CommentDO {
    private static final long serialVersionUID = 1L;

    private String nickname;
    private String title;
    private String avatar;
}
