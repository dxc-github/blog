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
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.CommentDO;
import top.putongren.dxcblog.model.query.CommentQuery;
import top.putongren.dxcblog.service.CommentService;

/**
 * @ClassName: CommentController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Controller
@RequestMapping("/management/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequiresPermissions("blog:comment:index")
    @GetMapping
    public String index() {
        return "management/comment";
    }

    @Log("查询评论")
    @PostMapping("/list")
    @ResponseBody
    public DataGridResult listComment(CommentQuery commentQuery){

        DataGridResult result = commentService.listComment(commentQuery);
        return result;
    }

    @Log("添加评论")
    @RequiresPermissions("blog:comment:add")
    @PostMapping("/add")
    @ResponseBody
    public DxcBlogResult addComment(CommentDO commentDO){
        return commentService.insert(commentDO);
    }

    @Log("评论状态修改")
    @RequiresPermissions("blog:comment:edit")
    @PostMapping("/edit/enable")
    @ResponseBody
    public DxcBlogResult editEnable(CommentDO commentDO){

        return commentService.updateEnableById(commentDO);
    }

}
