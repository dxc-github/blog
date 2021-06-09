package top.putongren.dxcblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.dao.TagReferDao;
import top.putongren.dxcblog.service.TagReferService;

import java.util.List;

/**
 * @ClassName: TagReferServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Service
public class TagReferServiceImpl implements TagReferService {
    @Autowired
    private TagReferDao tagReferDao;

    @Override
    public List<String> listNameByArticleId(long id) {
        return tagReferDao.listNameByArticleId(id);
    }
}
