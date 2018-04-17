package com.play.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/2/23.
 */
public class FileUploadUtil {

    public static void uploadFile(byte[] file,String filePath,String fileName) throws IOException {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath+"\\"+fileName);
        System.out.println("filepath="+filePath);
        fileOutputStream.write(file);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
