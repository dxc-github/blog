package top.putongren.dxcblog.service;

import org.quartz.SchedulerException;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.model.TaskDO;
import top.putongren.dxcblog.model.query.TaskQuery;

/**
 * @ClassName: TaskService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public interface TaskService {
    TaskDO get(Long id);

    DataGridResult list(TaskQuery query);

    int save(TaskDO taskScheduleJob);

    int update(TaskDO taskScheduleJob);

    int remove(Long id);

    int removeBatch(Long[] ids);

    void initSchedule() throws SchedulerException;

    void changeStatus(Long jobId, String jobStatus) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;

    void run(TaskDO scheduleJob) throws SchedulerException;
}
