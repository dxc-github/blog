package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.CateDO;

import java.util.List;

/**
 * @ClassName: CateService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface CateService {
    /**
     * 获取全部分类
     */
    List<CateDO> listAllCate();
    /**
     * 分页获取全部分类
     */
    DataGridResult listPageCate(int page, int limit);
    /**
     * 创建分类
     */
    DxcBlogResult save(CateDO cate);
    /**
     * 删除分类
     */
    DxcBlogResult delete(Long id);
    /**
     * 修改分类
     */
    DxcBlogResult updateById(CateDO cate);
}
