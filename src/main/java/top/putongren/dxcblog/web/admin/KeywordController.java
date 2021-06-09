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
import top.putongren.dxcblog.model.KeywordDO;
import top.putongren.dxcblog.model.query.BaseQuery;
import top.putongren.dxcblog.service.KeywordService;

/**
 * @ClassName: KeywordController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Controller
@RequestMapping("/management/keyword")
public class KeywordController {
    @Autowired
    private KeywordService keywordService;

    @RequiresPermissions("blog:keyword:index")
    @GetMapping
    public String index() {
        return "management/keyword";
    }

    @Log("关键字查询")
    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listKeyword(BaseQuery query) {
        DataGridResult result = keywordService.listPageKeyword(query);
        return result;
    }

    @Log("关键字添加")
    @RequiresPermissions("blog:keyword:add")
    @PostMapping("/add")
    @ResponseBody
    public DxcBlogResult add(KeywordDO keyword) {

        return keywordService.saveKeyword(keyword);
    }

    @Log("关键字删除")
    @RequiresPermissions("blog:keyword:delete")
    @PostMapping("/delete")
    @ResponseBody
    public DxcBlogResult delete(long id) {

        return keywordService.delete(id);
    }

    @Log("关键字状态修改")
    @RequiresPermissions("blog:keyword:editenable")
    @PostMapping("/edit/enable")
    @ResponseBody
    public DxcBlogResult editEnable(KeywordDO keyword) {

        return keywordService.update(keyword);
    }

    @Log("关键字修改")
    @RequiresPermissions("blog:keyword:editwords")
    @PostMapping("/edit/words")
    @ResponseBody
    public DxcBlogResult editWords(KeywordDO keyword) {

        return keywordService.update(keyword);
    }
}
