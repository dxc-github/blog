package top.putongren.dxcblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.Constant;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.model.SettingDO;
import top.putongren.dxcblog.service.SettingService;
import top.putongren.dxcblog.service.UploadService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SettingsController
 * @Description:
 * @Author dxc
 * @Date: 2021/5/25
 */
@Controller
@RequestMapping("/management/settings")
public class SettingsController {
    @Autowired
    private SettingService settingService;
    @Autowired
    private UploadService uploadService;

    @RequiresPermissions("sys:set:index")
    @GetMapping
    public String index(Model model) {
        List<SettingDO> settings = settingService.listAll();
        Map<String, Object> attributeMap = new HashMap<String, Object>();
        for (SettingDO setting : settings) {
            attributeMap.put(setting.getCode(), setting.getValue());
        }
        model.addAllAttributes(attributeMap);
        return "management/settings";
    }

    @RequiresPermissions("sys:qrcode:index")
    @GetMapping("/qrcode")
    public String qrcode(Model model) {
        model.addAttribute("alipay", settingService.getValueByCode(Constant.ALIPAY));
        model.addAttribute("wechat", settingService.getValueByCode(Constant.WECHAT_PAY));
        return "management/qrcode";
    }

    @Log("设置参数修改")
    @RequiresPermissions("blog:settings:edit")
    @PostMapping("/edit")
    @ResponseBody
    public DxcBlogResult editSettings(SettingDO setting) {

        return settingService.updateValueByCode(setting);
    }

    @Log("支付图片上传")
    @RequiresPermissions("blog:settings:upload")
    @PostMapping("/upload")
    @ResponseBody
    public DxcBlogResult upload(@RequestParam(value = "file", required = false) MultipartFile file, String payType) {
        if (file != null) {
            return uploadService.uploadQrcode(file, payType);
        } else {
            return DxcBlogResult.build(500, "上传文件为空！");
        }
    }

}
