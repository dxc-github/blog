package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.FileDO;

import java.util.List;

/**
 * @ClassName: FileDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
public interface FileDao {

    List<FileDO> listAllFile();

    int save(FileDO fileDO);

    int delete(Long id);

    int updateById(FileDO fileDO);
}
