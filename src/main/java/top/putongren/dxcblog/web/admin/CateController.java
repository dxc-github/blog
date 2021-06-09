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
import top.putongren.dxcblog.model.CateDO;
import top.putongren.dxcblog.service.CateService;

/**
 * @ClassName: CateController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Controller
@RequestMapping("/management/cate")
public class CateController {
    @Autowired
    private CateService cateService;

    @RequestMapping
    @RequiresPermissions("blog:cate:index")
    public String cate() {

        return "management/cate";
    }

    @Log("查询分类")
    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listCate(int page, int limit) {
        DataGridResult result = cateService.listPageCate(page, limit);
        return result;
    }

    @Log("添加分类")
    @RequiresPermissions("blog:cate:add")
    @PostMapping("/add")
    @ResponseBody
    public DxcBlogResult add(CateDO cate) {
        DxcBlogResult result = cateService.save(cate);
        return result;
    }

    @Log("删除分类")
    @RequiresPermissions("blog:cate:delete")
    @PostMapping("/delete")
    @ResponseBody
    public DxcBlogResult delete(long id) {

        return cateService.delete(id);
    }

    @Log("修改分类")
    @RequiresPermissions("blog:cate:edit")
    @PostMapping("/edit")
    @ResponseBody
    public DxcBlogResult edit(CateDO cate) {

        return cateService.updateById(cate);
    }
}
