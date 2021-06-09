package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.utils.FilterHtml;
import top.putongren.dxcblog.common.utils.IDUtils;
import top.putongren.dxcblog.dao.NoteDao;
import top.putongren.dxcblog.dao.TagDao;
import top.putongren.dxcblog.dao.TagReferDao;
import top.putongren.dxcblog.model.NoteDO;
import top.putongren.dxcblog.model.TagDO;
import top.putongren.dxcblog.model.TagReferDO;
import top.putongren.dxcblog.model.query.NoteQuery;
import top.putongren.dxcblog.model.vo.NoteVO;
import top.putongren.dxcblog.service.NoteService;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: NoteServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private TagReferDao tagReferDao;

    @Override
    public int countAllNote() {
        return noteDao.countAllNote();
    }

    @Override
    public NoteVO getLatestNote() {
        return noteDao.getLatestNote();
    }

    @Override
    @Transient
    public DxcBlogResult saveNote(NoteDO note, String tagName) {
        note.setId(IDUtils.genId());
        note.setTop(false);
        note.setIsShow(true);
        getTextContentByContent(note);
        note.setCreateTime(new Date());

        noteDao.saveNote(note);
        if (!StringUtils.isEmpty(tagName)) {
            //标签处理
            String[] tagNameArray = tagName.split(",");
            TagDO tag = new TagDO();
            TagReferDO tagRefer = new TagReferDO();
            for (String name : Arrays.asList(tagNameArray)) {
                if (tagDao.countByName(name) == 0) {
                    tag.setId(IDUtils.genId());
                    tag.setName(name);
                    tagDao.saveTag(tag);
                } else {
                    tag = tagDao.getTagByName(name);
                }
                tagRefer.setId(IDUtils.genId());
                tagRefer.setReferId(note.getId());
                tagRefer.setTagId(tag.getId());
                tagRefer.setIsShow(true);
                tagRefer.setType("2");
                tagReferDao.saveTagRefer(tagRefer);
            }
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DataGridResult listPageNote(NoteQuery query) {
        PageHelper.startPage(query.getPage(),query.getLimit());
        List<NoteDO> noteDOList = noteDao.listNoteByTitle(query.getTitle());
        //取记录总条数
        PageInfo<NoteDO> pageInfo = new PageInfo<NoteDO>(noteDOList);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(noteDOList);
        result.setCount(total);
        return result;
    }

    @Override
    public DxcBlogResult updateIsShowById(Long id, Boolean isShow) {
        NoteDO note = new NoteDO();
        note.setId(id);
        note.setIsShow(isShow);
        note.setUpdateTime(new Date());
        int issucc = noteDao.update(note);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult updateTopById(Long id, Boolean top) {
        NoteDO note = new NoteDO();
        note.setId(id);
        note.setTop(top);
        note.setUpdateTime(new Date());
        int issucc = noteDao.update(note);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult updateNote(NoteDO note, String tagName) {
        note.setUpdateTime(new Date());
        getTextContentByContent(note);
        noteDao.update(note);
        tagReferDao.deleteByReferId(note.getId());
        String[] tagNameArray = tagName.split(",");
        TagDO tag = new TagDO();
        TagReferDO tagRefer = new TagReferDO();
        for (String name : Arrays.asList(tagNameArray)) {
            if (tagDao.countByName(name) == 0) {
                tag.setId(IDUtils.genId());
                tag.setName(name);
                tagDao.saveTag(tag);
            } else {
                tag = tagDao.getTagByName(name);
            }
            tagRefer.setId(IDUtils.genId());
            tagRefer.setReferId(note.getId());
            tagRefer.setTagId(tag.getId());
            tagRefer.setIsShow(true);
            tagRefer.setType("2");
            tagReferDao.saveTagRefer(tagRefer);
        }
        return DxcBlogResult.ok();
    }

    @Override
    @Transactional
    public DxcBlogResult delete(Long id) {
        tagReferDao.deleteByReferId(id);
        noteDao.delete(id);
        return DxcBlogResult.ok();
    }

    @Override
    public NoteDO getNoteById(Long id) {
        return noteDao.getNoteById(id);
    }

    private void getTextContentByContent(NoteDO note) {
        String textContent = FilterHtml.filterHtml(note.getContent().trim());
        textContent = StringUtils.trimAllWhitespace(textContent);
        note.setTextContent(textContent);
    }
}
