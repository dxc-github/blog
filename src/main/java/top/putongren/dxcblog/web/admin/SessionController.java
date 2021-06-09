package top.putongren.dxcblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.service.SessionService;

/**
 * @ClassName: SessionController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@RequestMapping("/management/online")
@Controller
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @RequiresPermissions("sys:online:index")
    @GetMapping
    public String online() {
        return "management/online";
    }

    @Log("在线人数查询")
    @ResponseBody
    @RequestMapping("/list")
    public DataGridResult list(int page, int limit, String username) {
        return sessionService.list(page,limit,username);
    }

    @Log("在线人强制退出")
    @RequiresPermissions("sys:online:forceout")
    @ResponseBody
    @RequestMapping("/remove/{sessionId}")
    public DxcBlogResult forceLogout(@PathVariable("sessionId") String sessionId) {
        try {
            sessionService.removeUser(sessionId);
            return DxcBlogResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return DxcBlogResult.build(500, "强制退出失败！请联系管理员。");
        }

    }
}
