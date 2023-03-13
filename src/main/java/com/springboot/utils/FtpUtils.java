package com.springboot.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Properties;

public class FtpUtils {

    private static String host;//LinuxIP
    private static String port;//端口
    private static String username;//用户名
    private static String password;//用户密码
    private static String basePath;//基础路径

    static {
        FileInputStream file;
        try {
            file = new FileInputStream("src/main/java/com/springboot/linux.properties");
            Properties pro = new Properties();
            pro.load(file);
            host = pro.getProperty("host");
            port = pro.getProperty("port");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            basePath = pro.getProperty("basePath");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接FTP文件服务器
     */
    public static FTPClient login(){
        // 创建一个ftp客户端
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            // 连接 FTP 服务器
            ftp.connect(host,Integer.parseInt(port));
            // 登录
            ftp.login(username,password);
            reply = ftp.getReplyCode();
            // 如果没连接上，就断开服务器
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }
            System.out.println("FTP服务器连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     * 关闭连接FTP文件服务器
     */
    public static void closeFtp(FTPClient ftp){
        if (ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
                System.out.println("FTP服务器连接断开");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /***
     * @param directory 上传的文件夹
     * @param fileName 上传的文件的名称
     * @param input 文件以输入流形式
     * @return 文件名
     * */
    public static String uploadFile(String directory,String fileName, InputStream input) {
        String name = null;
        // 创建一个ftp客户端
        FTPClient ftp = login();
        try {
            // 设置文件类型 二进制的文件
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 创建文件夹
            ftp.makeDirectory(basePath+directory);
            // 设置上传目录
            ftp.changeWorkingDirectory(basePath+directory);
            // ftp server可能每次开启不同的端口来传输数据，
            // 但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞
            // 所以每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            ftp.enterLocalPassiveMode();
            // 存文件，返回值为true，则文件上传成功
            boolean b = ftp.storeFile(fileName,input);
            System.out.println(b);
            // 存完之后输入流关闭，ftp客户端注销
            // 获取响应状态
            System.out.println(ftp.getReplyString());
            input.close();
            if (b){
                // 上传状态改为 true 上传成功
                name = fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeFtp(ftp);
        }
        return name;
    }

    /*** 删除文件 **
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     *
     */
    public static boolean delete(String directory, String deleteFile){
        boolean b = false;
        FTPClient ftp = login();
        try {
            b = ftp.deleteFile(basePath + directory+"/"+deleteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeFtp(ftp);
        }
        return b;
    }

    /**
     * 下载文件
     * @param loadPath 下载到本地的位置
     * @param fileName 文件名
     * @param filePath 文件路径
     */
    public static boolean download(String loadPath,String fileName,String filePath){
        FTPClient ftp = login();
        boolean  flag = false;
        try {
            // 设置目录
            ftp.changeWorkingDirectory(basePath+filePath);
            // ftp server可能每次开启不同的端口来传输数据，
            // 但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞
            // 所以每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            ftp.enterLocalPassiveMode();
            File file = new File(loadPath+File.separatorChar+fileName);
            FileOutputStream os = new FileOutputStream(file);
            flag = ftp.retrieveFile(fileName, os);
            System.out.println(flag);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            closeFtp(ftp);
        }
        return flag;

    }



}
