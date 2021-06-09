package top.putongren.dxcblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.model.query.CommentQuery;
import top.putongren.dxcblog.service.CommentService;

/**
 * @ClassName: MessageController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Controller
@RequestMapping("/management/message")
public class MessageController {
    @Autowired
    private CommentService commentService;

    @RequiresPermissions("blog:message:index")
    @GetMapping
    public String index() {
        return "management/message";
    }

    @Log("留言查询")
    @PostMapping("/list")
    @ResponseBody
    public DataGridResult listMessage(CommentQuery commentQuery){
        commentQuery.setArticleId(new Long(666));
        DataGridResult result = commentService.listComment(commentQuery);
        return result;
    }
}
