package com.diandou.common.Authority;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by 胡志洁 on 2016/6/10.
 */
public class UploadFile {
    public static String uploadFile(MultipartHttpServletRequest multiRequest,String savePath) throws IOException {
        //取得request中的所有文件名
        Iterator<String> iter = multiRequest.getFileNames();
        String fileName = null;
        while(iter.hasNext()){
            //记录上传过程起始时的时间，用来计算上传时间
            int pre = (int) System.currentTimeMillis();
            //取得上传文件
            MultipartFile file = multiRequest.getFile(iter.next());
            if(file != null){
                //取得当前上传文件的文件名称
                String myFileName = file.getOriginalFilename();
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if(myFileName.trim() !=""){
                    System.out.println(myFileName);
                    //重命名上传后的文件名
                    fileName = ((Long)System.currentTimeMillis()).toString() + myFileName.trim();
                    String[] filePart = fileName.split("\\.");
                    if(filePart.length == 2) {
                        String sufix = filePart[1];
                        fileName = EncodeMD5.getMD5(filePart[0]) + "." + sufix;
                        //定义上传路径
                        String path = savePath + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                    }
                    else
                    {
                        return null;
                    }
                }
                else{
                    return null;
                }
            }

            //记录上传该文件后的时间
            int finaltime = (int) System.currentTimeMillis();
            System.out.println(finaltime - pre);
        }

        return fileName;
    }
}
