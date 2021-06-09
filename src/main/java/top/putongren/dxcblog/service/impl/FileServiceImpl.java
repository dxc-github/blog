package top.putongren.dxcblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DataGridResult;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.dao.FileDao;
import top.putongren.dxcblog.model.FileDO;
import top.putongren.dxcblog.model.query.FileQuery;
import top.putongren.dxcblog.service.FileService;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: FileServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;


    @Override
    public DataGridResult listAllFile(FileQuery fileQuery) {
        PageHelper.startPage(fileQuery.getPage(),fileQuery.getLimit());
        List<FileDO> fileDOList = fileDao.listAllFile();
        //取记录总条数
        PageInfo<FileDO> pageInfo = new PageInfo<FileDO>(fileDOList);
        long total = pageInfo.getTotal();
        //创建一个返回值对象
        DataGridResult result = new DataGridResult();
        result.setData(fileDOList);
        result.setCount(total);
        return result;
    }

    @Override
    public DxcBlogResult save(FileDO fileDO) {
        fileDO.setPost(new Date());
        int issucc = fileDao.save(fileDO);
        if(issucc<=0){
            return DxcBlogResult.build(500,"添加失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult delete(Long id) {
        int issucc = fileDao.delete(id);
        if(issucc<=0){
            return DxcBlogResult.build(500,"删除失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public DxcBlogResult updateById(FileDO fileDO) {
        int issucc = fileDao.updateById(fileDO);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }
}
