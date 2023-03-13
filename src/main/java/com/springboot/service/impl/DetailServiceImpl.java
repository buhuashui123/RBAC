package com.springboot.service.impl;

import com.springboot.in.DetailDao;
import com.springboot.pojo.Details;
import com.springboot.service.DetailService;
import com.springboot.utils.SftpUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DetailServiceImpl implements DetailService {

    @Resource
    private DetailDao detailDao;


    @Override
    public int addDetail(Details details) {
        Details details1 = detailDao.selectAll(details.getUserID());
        if (details1==null) {
            //创建一个新集合
            List<Details> list = new ArrayList<>();
            //把用户状态存入接中
            list.add(details);
            //调用持久层的添加方法
            return detailDao.addDetail(list);
        }
        return 0;
    }

    @Override
    public int DelDetails(Details details) {
        //创建一个新集合
        List<Details> list = new ArrayList<>();
        //把数据存入集合
        list.add(details);
        //调用持久层的删除方法
        return detailDao.DelDetails(list);
    }

    @Override
    public int UPDetail(Details details) {
        return detailDao.UPDetail(details);
    }

    @Override
    public Details selectAll(String userID) {
        return detailDao.selectAll(userID);
    }

    @Override
    public void addDetails(String userID,InputStream in) {
        //连接SFTP服务
        SftpUtils.login();
        //上传头像到永久目录
        String upload = SftpUtils.upload(userID, UUID.randomUUID() + ".jpg", in);
        //把用户头像文件名入库
        Details details = selectAll(userID);
        if (details!=null){
            DelDetails(new Details(userID,null,null));
            System.out.println("删除成功");
        }
        List<Details> list = new ArrayList<>();
        list.add(new Details(userID,null,upload));
        detailDao.addDetail(list);
        //关闭SFTP服务
        SftpUtils.closeServer();
    }
}
