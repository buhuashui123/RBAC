package com.springboot.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.sql.Clob;
import java.util.Properties;

public class FTPUtil {

    /** 本地字符编码 */
    private static  String LOCAL_CHARSET = "GBK";
    /** FTP协议里面，规定文件名编码为iso-8859-1 */
    private static  String SERVER_CHARSET = "ISO-8859-1";
    /** ftp服务器ip地址 */
    private static String FTP_ADDRESS;
    /** ftp服务器端口号 */
    private static String FTP_PORT;
    /** ftp服务器用户名 */
    private static String FTP_USERNAME;
    /** ftp服务器密码 */
    private static String FTP_PASSWORD;
    /** 附件路径 */
    private static String FTP_BASEPATH;

    /**
     * 读取外部文件
     */
    static {
        try {
            FileInputStream file = new FileInputStream("src/main/java/com/springboot/ftp.properties");
            Properties pro = new Properties();
            pro.load(file);
            FTP_ADDRESS = pro.getProperty("FTP_ADDRESS");
            FTP_PORT = pro.getProperty("FTP_PORT");
            FTP_USERNAME = pro.getProperty("FTP_USERNAME");
            FTP_PASSWORD = pro.getProperty("FTP_PASSWORD");
            FTP_BASEPATH = pro.getProperty("FTP_BASEPATH");
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
        int reply;
        try {
            // 连接 FTP 服务器
            ftp.connect(FTP_ADDRESS, Integer.parseInt(FTP_PORT));
            // 登录
            ftp.login(FTP_USERNAME,FTP_PASSWORD);
            // 验证FTP服务器是否登录成功
            reply = ftp.getReplyCode();
            // 如果没连接上，就断开服务器
            if(!FTPReply.isPositiveCompletion(reply)){
                ftp.disconnect();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftp;
    }

    /**
     * 上传文件
     * @param directory 上传的文件夹
     * @param fileName  上传的文件的名称
     * @param in        文件以输入流形式
     * @return 文件名
     */
    public static String uploadFile(String directory,String fileName, InputStream in){
        String name = null;
        // 创建一个ftp客户端
        FTPClient ftp = login();
        int reply;
        try {
            // 设置文件类型 二进制的文件
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 创建文件夹
            ftp.makeDirectory(FTP_BASEPATH+directory );
            // 设置上传目录
            ftp.changeWorkingDirectory(FTP_BASEPATH+directory);
            ftp.enterLocalPassiveMode();
            // 存文件，返回值为true，则文件上传成功
            boolean b = ftp.storeFile(fileName, in);
            System.out.println(b);
            // 获取响应状态
            System.out.println(ftp.getReplyString());
            // 存完之后输入流关闭，ftp客户端注销
            in.close();
            ftp.logout();
            if(b){
                // 上传状态改为 true 上传成功
                name = fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @return
     */
    public static boolean delete(String directory,String deleteFile){
        FTPClient ftp = login();
        boolean flag = false;
        try {
            flag = ftp.deleteFile(FTP_BASEPATH+directory+deleteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 删除文件
     * @param fileName 删除FTP文件名称
     * @param pathName 删除FTP文件夹路径
     * @return
     * @throws IOException
     */
    public static FTPClient deleteFile(String fileName,String pathName) throws IOException {
        FTPClient ftp = login();
        // 验证FTP服务器是否登录成功
        int replyCode  = ftp.getReplyCode();
        if(!FTPReply.isPositiveCompletion(replyCode )){
            return null;
        }
        //切换FTP目录
        ftp.changeWorkingDirectory(pathName);
        // 设置上传文件的类型为二进制类型
        // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
        if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {
            LOCAL_CHARSET = "UTF-8";
        }
        ftp.setControlEncoding(LOCAL_CHARSET);
        // 设置被动模式
        ftp.enterLocalPassiveMode();
        // 设置传输的模式
        ftp.setFileType(FTP.ASCII_FILE_TYPE);
        fileName = new String(fileName.getBytes(LOCAL_CHARSET),SERVER_CHARSET);
        int dele = ftp.dele(fileName);
        System.out.println(dele);
        if (ftp.isConnected()) {
            try {
                //退出登录
                ftp.logout();
                //关闭连接
                ftp.disconnect();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return ftp;
    }

    /**
     * 关闭连接
     * @param ftp 客户端
     */
    public static void closeFtp(FTPClient ftp){
        if(ftp.isConnected()){
            try {
                ftp.logout();
                ftp.disconnect();
                System.out.println("FTP服务器断开");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
            ftp.changeWorkingDirectory(FTP_BASEPATH+filePath);
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

    /**下载文件
     * @param FTP_BASEPATH 服务器文件路径
     * @param localPath    下载本地位置
     * @param fileName     文件名称
     */
    public static void downloadFtpFile(String FTP_BASEPATH,String localPath,String fileName){
        FTPClient ftp = login();
        FileOutputStream out = null;
        try {
            //建立连接
            ftp.login(FTP_USERNAME,FTP_PASSWORD);
            // 设置上传文件的类型为二进制类型
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
            if(FTPReply.isPositiveCompletion(ftp.sendCommand("OPST UTF8","NO"))){
                LOCAL_CHARSET="UTF-8";
            }
            ftp.setControlEncoding(LOCAL_CHARSET);
            //设置被动模式
            ftp.enterLocalActiveMode();
            //设置传输模式
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件 对中文文件名进行转换，否则中文名称的文件下载失败
            String fileNameTemp = new String(fileName.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
            //设置服务器文件路径
            ftp.changeWorkingDirectory(FTP_BASEPATH);
            //设置转换类型
            InputStream retrieveFileStream  = ftp.retrieveFileStream(fileNameTemp);
//            //下载文件
//            File file = new File(localPath + File.separatorChar + fileName);
//            //写入流
//            out = new FileOutputStream(file);
//            //写入位置
//            ftp.retrieveFile(fileName,out);

//            try {
//                out = new FileOutputStream(localPath+fileName);
//                byte[] b =  new byte[retrieveFileStream.available()];
//                int read = -1;
//                while((read = retrieveFileStream.read(b))!=-1) {
//                    out.write(b);
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            // 第二种方式下载：将输入流转成字节，再生成文件，这种方式方便将字节数组直接返回给前台jsp页面
            byte[] input2byte = input2byte(retrieveFileStream);
            byte2File(input2byte, localPath, fileName);
            if(retrieveFileStream!=null){
                retrieveFileStream.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("没有找到"+localPath+"文件");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("文件读取错误");
            e.printStackTrace();
        }finally {
            try {
                //防止空
                if(out!=null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(ftp.isConnected()){
                try {
                    //退出登录
                    ftp.logout();
                    //关闭连接
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 将字节数组转换为输入流
    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }
    // 将输入流转为byte[]
    public static final byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
//        if(inStream!=null){
            while ((rc = inStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
//        }

        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
    // 将byte[]转为文件
    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
