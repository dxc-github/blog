package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.utils.IDUtils;
import top.putongren.dxcblog.dao.KeywordDao;
import top.putongren.dxcblog.model.KeywordDO;
import top.putongren.dxcblog.model.query.BaseQuery;
import top.putongren.dxcblog.service.KeywordService;

import java.util.List;

/**
 * @ClassName: KeywordServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    private KeywordDao keywordDao;


    @Override
    public DataGridResult listPageKeyword(BaseQuery query) {
        PageHelper.startPage(query.getPage(),query.getLimit());
        List<KeywordDO> keywordDOList = keywordDao.listKeyword();
        //取记录总条数
        PageInfo<KeywordDO> pageInfo = new PageInfo<KeywordDO>(keywordDOList);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(keywordDOList);
        result.setCount(total);
        return result;
    }

    @Override
    public List<KeywordDO> listValidKeyword() {
        return keywordDao.listValidKeyword();
    }

    @Override
    public DxcBlogResult saveKeyword(KeywordDO keyword) {
        if(keywordDao.countByWords(keyword.getWords()) != 0){
            return DxcBlogResult.build(400, "关键字已存在！");
        }
        keyword.setId(IDUtils.genId());
        keyword.setEnable(true);
        int issucc = keywordDao.saveKeyword(keyword);
        if(issucc<=0){
            return DxcBlogResult.build(500,"添加失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult delete(long id) {
        int issucc = keywordDao.delete(id);
        if(issucc<=0){
            return DxcBlogResult.build(500,"删除失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult update(KeywordDO keyword) {
        int issucc = keywordDao.update(keyword);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }
}
