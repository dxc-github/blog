package top.putongren.dxcblog.dao;

import top.putongren.dxcblog.model.SettingDO;

import java.util.List;

/**
 * @ClassName: SettingDao
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public interface SettingDao {

    List<SettingDO> listAll();

    String getValueByCode(String code);

    int updateValueByCode(SettingDO setting);
}
