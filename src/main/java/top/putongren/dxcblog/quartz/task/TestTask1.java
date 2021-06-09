package top.putongren.dxcblog.quartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import top.putongren.dxcblog.common.utils.DateUtils;

import java.util.Date;

/**
 * @ClassName: TestTask1
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public class TestTask1 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("第一个定时任务"+ DateUtils.fullTime(new Date()));
    }
}
