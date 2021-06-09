package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.KeywordDO;

import java.util.List;

/**
 * @ClassName: KeywordDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
public interface KeywordDao {
    List<KeywordDO> listKeyword();

    List<KeywordDO> listValidKeyword();

    int saveKeyword(KeywordDO keyword);

    int countByWords(String words);

    int delete(long id);

    int update(KeywordDO keyword);
}
