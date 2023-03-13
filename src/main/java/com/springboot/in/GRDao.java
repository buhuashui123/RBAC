package com.springboot.in;

import com.springboot.pojo.GRName;
import com.springboot.pojo.GRPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GRDao {

    /**
     * 添加申请
     * @param list 群申请集合
     * @return 返回值大于0则发送申请成功，否则发送申请失败
     */
    int addGR(List<GRPojo> list);

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

    /**
     * 根据用户ID查出用户的所有群的申请
     * @return
     */
    List<GRName> selectAllByUserID(String userID);
}
