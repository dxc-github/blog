package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.CateDO;

import java.util.List;

/**
 * @ClassName: CateDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface CateDao {
    List<CateDO> listAllCate();

    int countByCode(String code);

    int save(CateDO cate);

    int delete(Long id);

    int updateById(CateDO cate);
}
