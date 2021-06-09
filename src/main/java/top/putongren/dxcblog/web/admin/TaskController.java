package top.putongren.dxcblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.enums.JobStatusEnum;
import top.putongren.dxcblog.model.TaskDO;
import top.putongren.dxcblog.model.query.TaskQuery;
import top.putongren.dxcblog.service.TaskService;

/**
 * @ClassName: TaskController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Controller
@RequestMapping("/management/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    @RequiresPermissions("sys:task:index")
    public String taskScheduleJob() {
        return "management/task";
    }

//    @RequiresPermissions("sys:task:toadd")
    @GetMapping("/add")
    public String taskAddScheduleJob() {
        return "management/task_add";
    }

    @Log("定时任务查询")
    @ResponseBody
    @PostMapping("/list")
    public DataGridResult list(TaskQuery query) {
        // 查询列表数据
        DataGridResult result = taskService.list(query);
        return result;
    }

    @Log("定时任务修改")
    @RequiresPermissions("sys:task:edit")
    @PostMapping("/edit")
    @ResponseBody
    public DxcBlogResult edit(TaskDO task) {
        TaskDO taskServer = taskService.get(task.getId());
        if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
            return DxcBlogResult.build(403, "修改之前请先停止任务！");
        }
        taskService.update(task);
        return DxcBlogResult.ok();
    }

    @Log("定时任务状态修改")
    @RequiresPermissions("sys:task:status")
    @PostMapping("/changeStatus/{id}")
    @ResponseBody
    public DxcBlogResult changeStatus(@PathVariable("id") Long id, Boolean jobStatus) {
        String status = jobStatus == true ? JobStatusEnum.RUNNING.getCode() : JobStatusEnum.STOP.getCode();
        try {
            taskService.changeStatus(id, status);
            return DxcBlogResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DxcBlogResult.build(403, "任务状态修改失败");
    }

    @Log("删除定时任务")
    @RequiresPermissions("sys:task:delete")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public DxcBlogResult remove(@PathVariable("id") Long id) {
        TaskDO taskServer = taskService.get(id);
        if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
            return DxcBlogResult.build(403, "删除前请先停止任务！");
        }
        if (taskService.remove(id) > 0) {
            return DxcBlogResult.ok();
        }
        return DxcBlogResult.build(403, "删除任务失败！");
    }

    @Log("运行定时任务")
    @RequiresPermissions("sys:task:run")
    @PostMapping("/run/{id}")
    @ResponseBody
    public DxcBlogResult run(@PathVariable("id") Long id) {
        TaskDO taskServer = taskService.get(id);
        try {
            if (JobStatusEnum.STOP.getCode().equals(taskServer.getJobStatus())) {
                return DxcBlogResult.build(403, "立即执行请先开启任务！");
            }
            taskService.run(taskServer);
            return DxcBlogResult.ok();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return DxcBlogResult.build(403, "立即运行任务失败！");
    }

    @Log("定时任务批量删除")
    @RequiresPermissions("sys:task:delete")
    @PostMapping("/removeBatch")
    @ResponseBody
    public DxcBlogResult removeBatch(@RequestParam("ids[]") Long[] ids) {
        for (Long id : ids) {
            TaskDO taskServer = taskService.get(id);
            if (JobStatusEnum.RUNNING.getCode().equals(taskServer.getJobStatus())) {
                return DxcBlogResult.build(403, "删除前请先停止任务！");
            }
        }
        taskService.removeBatch(ids);
        return DxcBlogResult.ok();
    }

    @Log("定时任务添加")
    @RequiresPermissions("sys:task:add")
    @ResponseBody
    @PostMapping("/save")
    public DxcBlogResult save(TaskDO task) {
        if (taskService.save(task) > 0) {
            return DxcBlogResult.ok();
        }
        return DxcBlogResult.build(403, "新增任务失败！");
    }
}
