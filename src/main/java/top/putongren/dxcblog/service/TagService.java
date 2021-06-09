package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.TagDO;
import top.putongren.dxcblog.model.query.TagQuery;

import java.util.List;

/**
 * @ClassName: TagService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface TagService {
    /**
     * 获取全部标签
     */
    List<TagDO> listAllTag();
    /**
     * 获取全部标签
     */
    DataGridResult listPageTag(TagQuery query);
    /**
     * 修改标签名称
     */
    DxcBlogResult updateById(TagDO tag);
    /**
     * 删除标签标签
     */
    DxcBlogResult delete(Long id);
}
