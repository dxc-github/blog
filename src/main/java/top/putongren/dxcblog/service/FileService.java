package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.FileDO;
import top.putongren.dxcblog.model.query.FileQuery;


/**
 * @ClassName: FileService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
public interface FileService {
    /**
     * 获取全部文件信息
     */
    DataGridResult listAllFile(FileQuery fileQuery);
    /**
     * 添加文件信息
     */
    DxcBlogResult save(FileDO fileDO);
    /**
     * 删除文件信息
     */
    DxcBlogResult delete(Long id);
    /**
     * 修改文件信息
     */
    DxcBlogResult updateById(FileDO fileDO);
}
