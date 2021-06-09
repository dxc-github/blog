package top.putongren.dxcblog.service;

import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.SettingDO;

import java.util.List;

/**
 * @ClassName: SettingService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
public interface SettingService {
    /**
     * 获取所有配置数据
     */
    List<SettingDO> listAll();
    /**
     * 根据code获取指定的value值
     */
    String getValueByCode(String code);

    DxcBlogResult updateValueByCode(SettingDO setting);
}
