package top.putongren.dxcblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.query.LogQuery;
import top.putongren.dxcblog.service.LogService;

/**
 * @ClassName: LogController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Controller
@RequestMapping("/management/log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequiresPermissions("sys:log:index")
    @GetMapping
    public String logIndex() {
        return "management/log";
    }

    @Log("日志查询")
    @ResponseBody
    @PostMapping("/list")
    public DataGridResult list(LogQuery query) {
        // 查询列表数据
        DataGridResult result = logService.list(query);
        return result;
    }

    @Log("日志删除")
    @RequiresPermissions("sys:log:delete")
    @PostMapping("/remove/{id}")
    @ResponseBody
    public DxcBlogResult remove(@PathVariable("id") Long id) {
        if (logService.remove(id) > 0) {
            return DxcBlogResult.ok();
        }
        return DxcBlogResult.build(403, "删除任务失败！");
    }

    @Log("日志批量删除")
    @RequiresPermissions("sys:log:delete")
    @PostMapping("/removeBatch")
    @ResponseBody
    public DxcBlogResult removeBatch(@RequestParam("ids[]") Long[] ids) {
        if (logService.removeBatch(ids) > 0) {
            return DxcBlogResult.ok();
        }
        return DxcBlogResult.build(403, "删除任务失败！");
    }
}
