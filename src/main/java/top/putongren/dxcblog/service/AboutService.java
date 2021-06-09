package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.AboutDO;

/**
 * @ClassName: AboutService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
public interface AboutService {
    /**
     * 更新关于信息
     */
    DxcBlogResult updateByTab(AboutDO about);
    /**
     * 跟进tab发现关于栏内容
     */
    AboutDO getAboutByTab(String tab);
}
