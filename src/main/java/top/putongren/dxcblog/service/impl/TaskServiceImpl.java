package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.dao.TaskDao;
import top.putongren.dxcblog.enums.JobStatusEnum;
import top.putongren.dxcblog.model.TaskDO;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.model.query.TaskQuery;
import top.putongren.dxcblog.model.vo.TaskVO;
import top.putongren.dxcblog.quartz.QuartzManager;
import top.putongren.dxcblog.service.TaskService;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: TaskServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private QuartzManager quartzManager;

    @Override
    public TaskDO get(Long id) {
        return taskDao.get(id);
    }

    @Override
    public DataGridResult list(TaskQuery query) {
        PageHelper.startPage(query.getPage(),query.getLimit());
        List<TaskVO> taskVOList = taskDao.listTaskVoByDesc(query.getDescription());
        PageInfo<TaskVO> pageInfo = new PageInfo<>(taskVOList);
        long total = pageInfo.getTotal();
        DataGridResult result = new DataGridResult();
        result.setData(taskVOList);
        result.setCount(total);
        return result;
    }

    @Override
    public int save(TaskDO task) {
        task.setJobStatus(JobStatusEnum.STOP.getCode());
        task.setCreateUser(((UserDO) SecurityUtils.getSubject().getPrincipal()).getUsername());
        task.setCreateTime(new Date());
        task.setUpdateUser(((UserDO)SecurityUtils.getSubject().getPrincipal()).getUsername());
        task.setUpdateTime(new Date());
        return taskDao.save(task);
    }

    @Override
    public int update(TaskDO task) {
        return taskDao.update(task);
    }

    @Override
    public int remove(Long id) {
        try {
            TaskDO task = get(id);
            quartzManager.deleteJob(task);
            return taskDao.remove(id);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int removeBatch(Long[] ids) {
        for (Long id : ids) {
            try {
                TaskDO task = get(id);
                quartzManager.deleteJob(task);
            } catch (SchedulerException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return taskDao.removeBatch(ids);
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<TaskDO> jobList = taskDao.list();
        for (TaskDO task : jobList) {
            if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
                quartzManager.addJob(task);
            }
        }
    }

    @Override
    public void changeStatus(Long jobId, String jobStatus) throws SchedulerException {
        TaskDO task = get(jobId);
        if (task == null) {
            return;
        }
        if (JobStatusEnum.STOP.getCode().equals(jobStatus)) {
            quartzManager.deleteJob(task);
            task.setJobStatus(JobStatusEnum.STOP.getCode());
        } else {
            task.setJobStatus(JobStatusEnum.RUNNING.getCode());
            quartzManager.addJob(task);
        }
        update(task);
    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {
        TaskDO task = get(jobId);
        if (task == null) {
            return;
        }
        if (JobStatusEnum.RUNNING.getCode().equals(task.getJobStatus())) {
            quartzManager.updateJobCron(task);
        }
        update(task);
    }

    @Override
    public void run(TaskDO task) throws SchedulerException {
        quartzManager.runJobNow(task);
    }
}
