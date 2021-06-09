package top.putongren.dxcblog.dao;

import org.apache.ibatis.annotations.Param;
import top.putongren.dxcblog.model.LogDO;

import java.util.List;

/**
 * @ClassName: LogDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public interface LogDao {
    LogDO get(Long id);

    List<LogDO> listByUsernameAndOperation(String username, String operation);

    int save(LogDO log);

    int update(LogDO log);

    int remove(Long id);

    int removeBatch(Long[] ids);
}
