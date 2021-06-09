package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.model.LogDO;
import top.putongren.dxcblog.model.query.LogQuery;

/**
 * @ClassName: LogService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public interface LogService {
    void save(LogDO logDO);

    DataGridResult list(LogQuery query);

    int remove(Long id);

    int removeBatch(Long[] ids);
}
