package top.putongren.dxcblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.dao.AboutDao;
import top.putongren.dxcblog.model.AboutDO;
import top.putongren.dxcblog.service.AboutService;

/**
 * @ClassName: AboutServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
@Service
public class AboutServiceImpl implements AboutService {
    @Autowired
    private AboutDao aboutDao;


    @Override
    public DxcBlogResult updateByTab(AboutDO about) {
        int issucc = aboutDao.updateByTab(about);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改失败");
        }
        return DxcBlogResult.ok();
    }

    @Override
    public AboutDO getAboutByTab(String tab) {
        return aboutDao.getAboutByTab(tab);
    }
}
