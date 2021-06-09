package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.utils.IDUtils;
import top.putongren.dxcblog.dao.ArticleDao;
import top.putongren.dxcblog.dao.CateDao;
import top.putongren.dxcblog.model.CateDO;
import top.putongren.dxcblog.service.CateService;

import java.util.List;

/**
 * @ClassName: CateServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/20
 */
@Service
public class CateServiceImpl implements CateService {
    @Autowired
    private CateDao cateDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<CateDO> listAllCate() {
        return cateDao.listAllCate();
    }

    @Override
    public DataGridResult listPageCate(int page, int limit) {
        PageHelper.startPage(page,limit);
        List<CateDO> cateDOList = cateDao.listAllCate();
        //取记录总条数
        PageInfo<CateDO> pageInfo = new PageInfo<CateDO>(cateDOList);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(cateDOList);
        result.setCount(total);
        return result;
    }

    @Override
    public DxcBlogResult save(CateDO cate) {
        int count = cateDao.countByCode(cate.getCode());
        if (count != 0) {
            return DxcBlogResult.build(400, "分类编码[" + cate.getName() + "]已存在！");
        }
        cate.setId(IDUtils.genId());
        int issucc = cateDao.save(cate);
        if(issucc<=0){
            return DxcBlogResult.build(500,"添加失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult delete(Long id) {
        int count = articleDao.countByCateId(id);
        if (count != 0) {
            return DxcBlogResult.build(400, "请删除分类文章后再删除！");
        }
        int issucc = cateDao.delete(id);
        if(issucc<=0){
            return DxcBlogResult.build(500,"删除失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult updateById(CateDO cate) {
        int issucc = cateDao.updateById(cate);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }
}
