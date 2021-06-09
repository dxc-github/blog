package top.putongren.dxcblog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ArticleVO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String cateName;
    private String cover;
    private String summary;
    private String content;
    private String textContent;
    private Integer views;
    private Integer approveCnt;
    private Boolean commented;
    private Boolean appreciable;
    private Boolean draft;
    private Boolean top;
    private String authorName;
    private Date createTime;
}
