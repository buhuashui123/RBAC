package com.springboot.service;

import com.springboot.pojo.Details;

import java.io.InputStream;
import java.util.List;

public interface DetailService {

    /**
     * 添加多个用户头像，状态
     * @param details  用户详情对象
     * @return
     */
    int addDetail(Details details);


    /**
     * 删除多个用户详情
     * @param details 用户详情对象
     * @return
     */
    int DelDetails(Details details);

    /**
     * 修改用户头像
     * @param details 用户详情对象
     * @return
     */
    int UPDetail(Details details);

    /**
     * 根据用户ID 查询用户的头像和账号状态
     * @return
     */
    Details selectAll(String userID);

    void addDetails(String userID, InputStream in);

}
