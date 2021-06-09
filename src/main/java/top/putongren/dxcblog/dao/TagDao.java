package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.TagDO;

import java.util.List;

/**
 * @ClassName: TagDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface TagDao {
    List<TagDO> listAllTag();

    List<TagDO> listTagByName(String name);

    TagDO getTagByName(String name);

    int updateById(TagDO tag);

    int delete(Long id);

    int saveTag(TagDO tag);

    int countByName(String name);

    List<TagDO> listTagByReferId(Long articleId);
}
