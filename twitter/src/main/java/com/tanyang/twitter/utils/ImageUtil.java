package com.tanyang.twitter.utils;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;


public class ImageUtil {

    private  String fileUpPath;

    public ImageUtil() {
        fileUpPath=PropertyUtil.getProperty("fileUpPath");
    }

    public  String getImageBase64Coder(String imgname){
        //System.out.println(fileUpPath);
        try {
            StringBuffer result=new StringBuffer("data:image/jpg;base64,");
            File file=new File(fileUpPath+imgname);
            FileInputStream fileInputStream=new FileInputStream(file);
            BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream((int)file.length());

            int buf_size=1024;
            byte[] buffer=new byte[buf_size];
            int len=0;
            while(-1 != (len=bufferedInputStream.read(buffer,0,buf_size))){
                byteArrayOutputStream.write(buffer,0,len);
            }
            String content=new String(Base64.encode(byteArrayOutputStream.toByteArray())).trim();

            return result.append(content).toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
