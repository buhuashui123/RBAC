package com.springboot.service;

import com.springboot.pojo.GRName;
import com.springboot.pojo.GRPojo;

import java.util.List;

public interface GRService {

    /**
     * 添加申请
     * @param grPojo 群申请
     * @return 返回值大于0则发送申请成功，否则发送申请失败
     */
    int addGR(GRPojo grPojo);

    /**
     * 删除申请
     * @param grPojo 群申请
     * @return 返回值大于0则删除申请成功，否则删除申请失败
     */
    int DelGR(GRPojo grPojo);

    /**
     * 查出群ID查出当前群的申请
     * @return
     */
    List<GRPojo> selectAllByID(String id);

    List<GRName> selectAllByUserID(String userID);
}
