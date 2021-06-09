package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.TagReferDO;
import top.putongren.dxcblog.model.vo.TagVO;

import java.util.List;

/**
 * @ClassName: TagReferDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface TagReferDao {
    int countByTagId(Long tagId);

    int saveTagRefer(TagReferDO tagRefer);

    List<String> listNameByArticleId(long referId);

    int deleteByReferId(long referId);

    List<TagVO> listNameAndCnt();
}
