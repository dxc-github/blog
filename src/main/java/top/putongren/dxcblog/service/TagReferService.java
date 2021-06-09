package top.putongren.dxcblog.service;

import java.util.List;

/**
 * @ClassName: TagReferService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface TagReferService {
    List<String> listNameByArticleId(long id);
}
