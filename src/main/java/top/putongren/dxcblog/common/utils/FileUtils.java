package top.putongren.dxcblog.common.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @ClassName: FileUtils
 * @Description: 文件上传下载工具类
 * @Author dxc
 * @Date: 2021/5/21
 */
public class FileUtils {
    /**
     * 文件上传
     */
    public static String fileUpload(MultipartFile file, String filePath){
        File baseFile = new File(filePath);

        if(!baseFile.exists()){
            baseFile.mkdir();
        }
        String oldFileName = file.getOriginalFilename();
        String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;
        File trainFile = new File(baseFile, fileName);
        try{
            file.transferTo(trainFile);
        }catch (IllegalStateException e){
            e.printStackTrace();
            return "";
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
        return fileName;
    }

    /**
     * 文件下载
     */
    public static Boolean fileDownload(String fileName, String downloadName, HttpServletResponse response){
        try{
            response.setContentType("octets/stream");
            response.addHeader("Content-Type", "text/html; charset=utf-8");
            String downLoadName = new String(downloadName.getBytes("gbk"), "iso8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + downLoadName);

            FileInputStream fileInputStream = new FileInputStream(fileName);
            OutputStream out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
