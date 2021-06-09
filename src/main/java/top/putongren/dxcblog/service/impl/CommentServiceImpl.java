package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.dao.CommentDao;
import top.putongren.dxcblog.model.CommentDO;
import top.putongren.dxcblog.model.query.CommentQuery;
import top.putongren.dxcblog.model.vo.CommentVO;
import top.putongren.dxcblog.service.CommentService;

import java.util.List;

/**
 * @ClassName: CommentServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public int countAllComment() {
        return commentDao.countAllComment();
    }

    @Override
    public CommentVO getLatestComment() {
        return commentDao.getLatestComment();
    }

    @Override
    public DataGridResult listComment(CommentQuery query) {
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<CommentVO> list = commentDao.listComment(query);
        //取记录总条数
        PageInfo<CommentVO> pageInfo = new PageInfo<CommentVO>(list);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(list);
        result.setCount(total);
        return result;
    }

    @Override
    public DxcBlogResult insert(CommentDO comment) {
        return null;
    }

    @Override
    public DxcBlogResult updateEnableById(CommentDO comment) {
        int issucc = commentDao.updateEnableById(comment);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改评论状态失败");
        }
        return DxcBlogResult.ok();
    }
}
