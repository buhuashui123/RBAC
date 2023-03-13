package com.springboot.text;

import com.jcraft.jsch.SftpException;
import com.springboot.utils.FTPUtil;
import com.springboot.utils.FtpUtils;
import com.springboot.utils.SftpUtils;

import java.io.*;

public class Demo {

    public static void main(String[] args) throws SftpException {
        String path = "C:/Users/小马/Pictures/Saved Pictures/111.jpg";
//        FileInputStream in = null;
//        try {
//             in = new FileInputStream(path);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        FtpUtils.uploadFile("files","222.jpg",in);

//        boolean b = FTPUtil.download("E:/", "222.jpg", "/home/xiaoma/files");
//        if(!b){
//            System.out.println("下载失败");
//        }else {
//            System.out.println("下载成功");
//        }


//        String path = "D:/休闲娱乐/头像/二哈.jpg";
//        InputStream in = null;
//        try {
//            in =    new FileInputStream(path);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        String s = FtpUtils.uploadFile("userID","123.jpg",in);
//        System.out.println(s);
        //boolean b = FtpUtils.download("d:/", "456.jpg", "20220812");
//        if (!b){
//            System.out.println("下载失败");
//        }else {
//            System.out.println("下载成功");
//        }

//        boolean b = FtpUtils.delete("userID", "123.jpg");
//        System.out.println(b);


//        String str = "^[1][3-6][0-9][0-9]{8}";
//        String phone = "15832013175";
//        System.out.println(phone.matches(str));

//        SftpUtils utils = new SftpUtils("root",
//                "laoma666",
//                "192.168.80.128",
//                String.valueOf(22));
//        utils.login();
////        //上传文件测试
//        File file = new File(path);
//        InputStream in = null;
//        try {
//             in = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        utils.upload(null, "6.jpg", in);
//
//        utils.download("/home/mysftp/UserID","456.jpg","d:/123.jpg");
//        Vector<?> objects = utils.listFiles("/home/mysftp/UserID");

//        SftpUtils utils = new SftpUtils();
//        utils.listFiles("/home/xiaoma");
//        //utils.delete("/home/mysftp/UserID","456.jpg");
//        utils.closeServer();

        
    }
}
