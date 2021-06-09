package top.putongren.dxcblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ArticleDO
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Long cateId;
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
    private Long authorId;
    private Date createTime;
    private Date updateTime;
}
