package com.architect.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author wenxiong.jia
 * @since 2019/3/3
 */
public class FileUtil {
    public static void uploadFile(MultipartFile file, String filePath) throws Exception {
        File targetFileDir = new File(filePath);
        if (!targetFileDir.exists()) {
            if (targetFileDir.mkdirs()) {
                OutputStream outputStream = new FileOutputStream(filePath + file.getOriginalFilename());
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}
