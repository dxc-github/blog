package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.AboutDO;

import java.util.List;

/**
 * @ClassName: AboutDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/24
 */
public interface AboutDao {
    int updateByTab(AboutDO about);

    AboutDO getAboutByTab(String tab);

    List<AboutDO> listAll();
}
