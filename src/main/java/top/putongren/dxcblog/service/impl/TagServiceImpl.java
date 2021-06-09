package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.dao.TagDao;
import top.putongren.dxcblog.dao.TagReferDao;
import top.putongren.dxcblog.model.TagDO;
import top.putongren.dxcblog.model.query.TagQuery;
import top.putongren.dxcblog.service.TagService;

import java.util.List;

/**
 * @ClassName: TagServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    @Autowired
    private TagReferDao tagReferDao;


    @Override
    public List<TagDO> listAllTag() {
        return tagDao.listAllTag();
    }

    @Override
    public DataGridResult listPageTag(TagQuery query) {
        PageHelper.startPage(query.getPage(),query.getLimit());
        List<TagDO> list;
        if(StringUtils.isBlank(query.getName())){
            list = tagDao.listAllTag();
        }else {
            list = tagDao.listTagByName(query.getName());
        }
        //取记录总条数
        PageInfo<TagDO> pageInfo = new PageInfo<TagDO>(list);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(list);
        result.setCount(total);
        return result;
    }

    @Override
    public DxcBlogResult updateById(TagDO tag) {
        int issucc = tagDao.updateById(tag);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult delete(Long id) {
        int count = tagReferDao.countByTagId(id);
        if(count != 0){
            return DxcBlogResult.build(400,"请删除标签相关文章/笔记后再删除！");
        }
        int issucc = tagDao.delete(id);
        if(issucc<=0){
            return DxcBlogResult.build(500,"删除失败");
        }
        return DxcBlogResult.ok();
    }
}
