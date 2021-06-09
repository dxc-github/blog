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
import top.putongren.dxcblog.model.FileDO;
import top.putongren.dxcblog.model.query.FileQuery;
import top.putongren.dxcblog.service.FileService;

/**
 * @ClassName: FileController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Controller
@RequestMapping("/management/file")
public class FileController {
    @Autowired
    private FileService fileService;


    @RequiresPermissions("blog:file:index")
    @RequestMapping
    public String file() {
        return "management/file";
    }

    @Log("资源列表查询")
    @GetMapping("/list")
    @ResponseBody
    public DataGridResult listFile(FileQuery query){
        return fileService.listAllFile(query);
    }

    @Log("资源添加")
    @RequiresPermissions("blog:file:add")
    @PostMapping("/add")
    @ResponseBody
    public DxcBlogResult addFile(FileDO fileDO){
        return fileService.save(fileDO);
    }

    @Log("删除资源")
    @RequiresPermissions("blog:file:delete")
    @PostMapping("/delete")
    @ResponseBody
    public DxcBlogResult deleteFile(Long id){
        return fileService.delete(id);
    }

    @Log("修改资源")
    @RequiresPermissions("blog:file:edit")
    @PostMapping("/edit")
    @ResponseBody
    public DxcBlogResult editFile(FileDO fileDO){
        return fileService.updateById(fileDO);
    }

}


