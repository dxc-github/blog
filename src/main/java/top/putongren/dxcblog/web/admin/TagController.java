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
import top.putongren.dxcblog.model.TagDO;
import top.putongren.dxcblog.model.query.TagQuery;
import top.putongren.dxcblog.service.TagService;

/**
 * @ClassName: TagController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Controller
@RequestMapping("/management/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequiresPermissions("blog:tag:index")
    @RequestMapping
    public String tag() {
        return "management/tag";
    }

    @Log("标签查询")
    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listTag(TagQuery query) {
        DataGridResult result = tagService.listPageTag(query);
        return result;
    }

    @Log("标签修改")
    @RequiresPermissions("blog:tag:edit")
    @PostMapping("/edit")
    @ResponseBody
    public DxcBlogResult edit(TagDO tag) {

        return tagService.updateById(tag);
    }

    @Log("标签删除")
    @RequiresPermissions("blog:tag:delete")
    @PostMapping("/delete")
    @ResponseBody
    public DxcBlogResult delete(long id) {

        return tagService.delete(id);
    }
}
