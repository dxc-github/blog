package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.NoteDO;
import top.putongren.dxcblog.model.query.NoteQuery;
import top.putongren.dxcblog.model.vo.NoteVO;

/**
 * @ClassName: NoteService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface NoteService {
    /**
     * 获取总的笔记数
     */
    int countAllNote();
    /**
     * 获取最新笔记
     */
    NoteVO getLatestNote();
    /**
     * 新增保存笔记
     */
    DxcBlogResult  saveNote(NoteDO note, String tagName);
    /**
     * 查询笔记列表
     */
    DataGridResult listPageNote(NoteQuery query);
    /**
     * 修改状态
     */
    DxcBlogResult  updateIsShowById(Long id, Boolean isShow);
    /**
     * 修改置顶
     */
    DxcBlogResult  updateTopById(Long id, Boolean top);
    /**
     * 修改笔记
     */
    DxcBlogResult  updateNote(NoteDO note, String tagName);
    /**
     * 删除笔记
     */
    DxcBlogResult  delete(Long id);
    /**
     * 根据ID获取笔记
     */
    NoteDO getNoteById(Long id);
}
