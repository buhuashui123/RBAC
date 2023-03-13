package com.springboot.utils;

import com.jcraft.jsch.*;
import org.apache.commons.lang.ArrayUtils;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

public class SftpUtils {

    /* 连接通道 */
    private static ChannelSftp sftp;
    /* 连接session */
    private static Session session;
    /* 用户名 */
    private static String username;
    /* 密码 */
    private static String password;
    /* 私钥 */
    private static String privateKey;
    /* ip地址 */
    private static String host;
    /* 端口 */
    private static String port;
    private static String SFTP_BASEPATH;

    /**
     * 读取外部文件
     */
    static {
        try {
            FileInputStream file = new FileInputStream("src/main/java/com/springboot/ftp.properties");
            Properties pro = new Properties();
            pro.load(file);
            host = pro.getProperty("SFTP_ADDRESS");
            port = pro.getProperty("SFTP_PORT");
            username = pro.getProperty("SFTP_USERNAME");
            password = pro.getProperty("SFTP_PASSWORD");
            SFTP_BASEPATH = pro.getProperty("SFTP_BASEPATH");
            System.out.println(SFTP_BASEPATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //无参构造
    public SftpUtils() {
    }

    /**
     * 构造基于密码认证的sftp对象
     * @param username
     * @param password
     * @param host
     * @param port
     */
    public SftpUtils(String username, String password, String host, String port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     * @param username
     * @param host
     * @param port
     * @param privateKey
     */
//    public SftpUtils(String username,String host,int port,String privateKey){
//        this.username = username;
//        this.host = host;
//        this.port = port;
//        this.privateKey = privateKey;
//    }


    /**
     * 连接SFTP文件服务器
     */
    public static void login() {
            try {
                JSch jsch = new JSch();
                if (privateKey != null){
                    jsch.addIdentity(privateKey);//设置私密
                }
                session = jsch.getSession(username,host, Integer.parseInt(port));
                if (password != null) {
                    session.setPassword(password);
                }
                Properties config = new Properties();
                config.put("StrictHostKeyChecking", "no");
                //为session对象设置properties
                session.setConfig(config);
                //关闭认证
                session.connect();
                //创建sftp通信通道
                Channel channel = session.openChannel("sftp");
                channel.connect();
                sftp = (ChannelSftp) channel;
            } catch (JSchException e) {
                e.printStackTrace();
            }
    }

    /**
     * 关闭连接 server
     */
    public static void closeServer(){
        if (sftp!=null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session!=null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    /*** 将输入流的数据上传到sftp作为文件。
     * 文件完整路径=basePath+directory
//     * @param SFTP_BASEPATH 服务器的基础路径
     * @param directory 上传到该目录
     * @param sftpFileName sftp端文件名
     * @param input 输入流 */
    public static String upload( String directory, String sftpFileName, InputStream input){

        try {
            sftp.cd(SFTP_BASEPATH);
            sftp.cd(directory);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String[] dirs = directory.split("/");
            String tempPath = SFTP_BASEPATH;
            if (!ArrayUtils.isEmpty(dirs)) {
                for (String dir : dirs) {
                    //使用绝对路径，相对路径会出现一些bug
                    tempPath = tempPath + "/"+dir;
                    //tempPath += "/" + dir;
                    try {
                        sftp.cd(tempPath);
                    } catch (SftpException sftpException) {
                        try {
                            sftp.mkdir(tempPath);
                            sftp.cd(tempPath);
                        } catch (SftpException exception) {
                            exception.printStackTrace();
                        }
                    }
                    System.out.println("上传成功");
                }
            }
        }
        try {
            //上传文件
            sftp.put(input,sftpFileName);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return sftpFileName;
    }

    /*** 下载文件。 **
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * */
    public static void download(String directory, String downloadFile, String saveFile){
        try {
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            File file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (SftpException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*** 列出目录下的文件 **
     * @param directory 要列出的目录
     * */
    public static Vector<?> listFiles(String directory){
        Vector<?> vor = null;
        try {
          vor =  sftp.ls(directory);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return vor;
    }

    /*** 删除文件 **
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件 */
    public static void delete(String directory, String deleteFile){
        try {
            //进入文件夹
            sftp.cd(SFTP_BASEPATH+directory);
            //调用删除文件的方法
            sftp.rm(deleteFile);
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }





}
