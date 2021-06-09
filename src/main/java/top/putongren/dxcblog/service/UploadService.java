package top.putongren.dxcblog.service;

import org.springframework.web.multipart.MultipartFile;
import top.putongren.dxcblog.common.DxcBlogResult;
import top.putongren.dxcblog.common.NKBlogResult;

/**
 * @ClassName: UploadService
 * @Description:
 * @Author dxc
 * @Date: 2021/5/21
 */
public interface UploadService {
    DxcBlogResult upload(MultipartFile file);

    DxcBlogResult uploadQrcode(MultipartFile file, String type);

    NKBlogResult uploadNK(MultipartFile file);
}
