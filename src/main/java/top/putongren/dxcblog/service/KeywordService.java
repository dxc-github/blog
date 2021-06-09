package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.KeywordDO;
import top.putongren.dxcblog.model.query.BaseQuery;

import java.util.List;

/**
 * @ClassName: KeywordService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
public interface KeywordService {
    /**
     * 分页查询关键字
     */
    DataGridResult listPageKeyword(BaseQuery query);
    /**
     * 查询所有有效的关键字
     */
    List<KeywordDO> listValidKeyword();
    /**
     * 保存关键字
     */
    DxcBlogResult saveKeyword(KeywordDO keyword);
    /**
     * 删除关键字
     */
    DxcBlogResult delete(long id);
    /**
     * 修改关键字状态/内容
     */
    DxcBlogResult update(KeywordDO keyword);
}
