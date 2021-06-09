package top.putongren.dxcblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.putongren.dxcblog.common.Constant;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.NKBlogResult;
import top.putongren.dxcblog.common.utils.FileUtils;
import top.putongren.dxcblog.dao.SettingDao;
import top.putongren.dxcblog.model.SettingDO;
import top.putongren.dxcblog.service.UploadService;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UploadServiceImpl
 * @Description:
 * @Author dxc
 * @Date: 2021/5/21
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private SettingDao settingDao;

    @Override
    public DxcBlogResult upload(MultipartFile file) {
        boolean contains = file.getContentType().contains("image/");
        if (!contains){
            return DxcBlogResult.build(500, "上传失败，图片格式不合法！");
        }
        String name = null;
        try{
            name = FileUtils.fileUpload(file, Constant.UPLOAD_PATH);
        }catch (Exception e){
            e.printStackTrace();
            return DxcBlogResult.build(500,e.getMessage());
        }
        return DxcBlogResult.ok(Constant.ACCESS_URL + name);
    }

    @Override
    public DxcBlogResult uploadQrcode(MultipartFile file, String type) {
        boolean contains = file.getContentType().contains("image/");
        if (!contains){
            return DxcBlogResult.build(500, "上传失败，图片格式不合法！");
        }
        String name = null;
        String path = null;
        try{
            name = FileUtils.fileUpload(file, Constant.UPLOAD_PATH);
            path = Constant.ACCESS_URL + name;
            SettingDO settingDO = new SettingDO();
            settingDO.setCode(type);
            settingDO.setValue(path);
            int issucc = settingDao.updateValueByCode(settingDO);
            if(issucc<=0){
                return DxcBlogResult.build(500,"上传支付图片失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return DxcBlogResult.build(500,e.getMessage());
        }
        return DxcBlogResult.ok(Constant.ACCESS_URL + name);
    }

    @Override
    public NKBlogResult uploadNK(MultipartFile file) {
        boolean contains = file.getContentType().contains("image/");
        if (!contains){
            return NKBlogResult.build(500, "上传失败，图片格式不合法！");
        }
        String name = null;
        try{
            name = FileUtils.fileUpload(file, Constant.UPLOAD_PATH);
        }catch (Exception e){
            e.printStackTrace();
            return NKBlogResult.build(500,e.getMessage());
        }

        Map<String,Object> image = new HashMap<>();
        image.put("url",Constant.ACCESS_URL + name);
        return NKBlogResult.ok(image);
    }
}
