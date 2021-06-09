package top.putongren.dxcblog.dao;

import org.apache.ibatis.annotations.Param;
import top.putongren.dxcblog.model.NoteDO;
import top.putongren.dxcblog.model.vo.NoteVO;

import java.util.List;

/**
 * @ClassName: NoteDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
public interface NoteDao {
    int countAllNote();

    NoteVO getLatestNote();

    int saveNote(NoteDO note);

    List<NoteDO> listNoteByTitle(@Param("title")String title);

    int update(NoteDO note);

    int delete(long id);

    NoteDO getNoteById(Long id);
}
