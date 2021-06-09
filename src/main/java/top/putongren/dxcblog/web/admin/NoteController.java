package top.putongren.dxcblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.NoteDO;
import top.putongren.dxcblog.model.query.NoteQuery;
import top.putongren.dxcblog.service.NoteService;
import top.putongren.dxcblog.service.TagReferService;
import top.putongren.dxcblog.web.BaseController;

import java.util.Arrays;

/**
 * @ClassName: NoteController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/21
 */
@Controller
@RequestMapping("/management/note")
public class NoteController extends BaseController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private TagReferService tagReferService;

    @RequiresPermissions("blog:note:index")
    @GetMapping
    public String note() {
        return "management/note";
    }

    @RequiresPermissions("blog:note:index")
    @GetMapping("/index")
    public String index() {
        return "management/notes";
    }

    @Log("随笔添加")
    @RequiresPermissions("blog:note:add")
    @PostMapping("/add")
    @ResponseBody
    public DxcBlogResult add(NoteDO note, String tagName) {
        return noteService.saveNote(note, tagName);
    }

    @Log("随笔查询")
    @PostMapping("/list")
    @ResponseBody
    public DataGridResult listNote(NoteQuery query) {
        DataGridResult result = noteService.listPageNote(query);
        return result;
    }

    @Log("编辑笔记显示状态")
    @RequiresPermissions("blog:note:show")
    @PostMapping("/edit/show/{id}")
    @ResponseBody
    public DxcBlogResult show(@PathVariable("id") Long id, Boolean show) {

        return noteService.updateIsShowById(id, show);
    }

    @Log("随笔置顶状态修改")
    @RequiresPermissions("blog:note:top")
    @PostMapping("/edit/top/{id}")
    @ResponseBody
    public DxcBlogResult top(@PathVariable("id") Long id, Boolean top) {

        return noteService.updateTopById(id, top);
    }

    @Log("随笔删除")
    @RequiresPermissions("blog:note:delete")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public DxcBlogResult delete(@PathVariable("id") Long id) {

        return noteService.delete(id);
    }

    @RequiresPermissions("blog:note:editIndex")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("editNote", noteService.getNoteById(id));
        String[] tagArray = tagReferService.listNameByArticleId(id).toArray(new String[]{});
        String tags = Arrays.toString(tagArray);
        model.addAttribute("noteTags", tags.substring(1, tags.length() - 1));
        return "management/note_edit";
    }

    @Log("随笔修改")
    @RequiresPermissions("blog:note:edit")
    @PostMapping("/doEdit")
    @ResponseBody
    public DxcBlogResult doEdit(NoteDO note, String tagNames) {

        return noteService.updateNote(note, tagNames);
    }
}
