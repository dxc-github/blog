package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.dao.LogDao;
import top.putongren.dxcblog.model.LogDO;
import top.putongren.dxcblog.model.query.LogQuery;
import top.putongren.dxcblog.service.LogService;

import java.util.List;

/**
 * @ClassName: LogServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public void save(LogDO logDO) {
        logDao.save(logDO);
    }

    @Override
    public DataGridResult list(LogQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<LogDO> list = logDao.listByUsernameAndOperation(query.getUsername(),query.getOperation());
        //取记录总条数
        PageInfo<LogDO> pageInfo = new PageInfo<LogDO>(list);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(list);
        result.setCount(total);
        return result;
    }

    @Override
    public int remove(Long id) {
        return logDao.remove(id);
    }

    @Override
    public int removeBatch(Long[] ids) {
        return logDao.removeBatch(ids);
    }
}
