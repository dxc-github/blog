package top.putongren.dxcblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.dao.SettingDao;
import top.putongren.dxcblog.model.SettingDO;
import top.putongren.dxcblog.service.SettingService;

import java.util.List;

/**
 * @ClassName: SettingServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingDao settingDao;


    @Override
    public List<SettingDO> listAll() {
        return settingDao.listAll();
    }

    @Override
    public String getValueByCode(String code) {
        return settingDao.getValueByCode(code);
    }

    @Override
    public DxcBlogResult updateValueByCode(SettingDO setting) {
        int issucc = settingDao.updateValueByCode(setting);
        if(issucc<=0){
            return DxcBlogResult.build(500,"修改配置失败");
        }
        return DxcBlogResult.ok();
    }
}
