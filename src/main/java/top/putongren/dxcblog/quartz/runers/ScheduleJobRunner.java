package top.putongren.dxcblog.quartz.runers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.putongren.dxcblog.service.TaskService;

/**
 * @ClassName: ScheduleJobRunner
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Component
@Order(value = 1)
public class ScheduleJobRunner implements CommandLineRunner {
    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        try {
            taskService.initSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
