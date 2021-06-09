package top.putongren.dxcblog.dao;

import org.apache.ibatis.annotations.Param;
import top.putongren.dxcblog.model.TaskDO;
import top.putongren.dxcblog.model.vo.TaskVO;

import java.util.List;

/**
 * @ClassName: TaskDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public interface TaskDao {
    TaskDO get(Long id);

    List<TaskDO> list();

    List<TaskVO> listTaskVoByDesc(String desc);

    int save(TaskDO task);

    int update(TaskDO task);

    int remove(Long id);

    int removeBatch(Long[] ids);
}
