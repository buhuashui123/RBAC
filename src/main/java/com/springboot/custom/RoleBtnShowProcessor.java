package com.springboot.custom;

import com.springboot.pojo.Menu;
import com.springboot.pojo.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.standard.processor.AbstractStandardConditionalVisibilityTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;

/**
 * 自定义属性
 */
public class RoleBtnShowProcessor extends AbstractStandardConditionalVisibilityTagProcessor {
    private RedisTemplate<Object,Object> redisTemplate;
    public RoleBtnShowProcessor(RedisTemplate<Object,Object> redisTemplate) {
        super(
                TemplateMode.HTML,
                "bs",
                "btn",
                1000);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected boolean isVisible(ITemplateContext context, IProcessableElementTag elementTag, AttributeName attributeName, String s) {
        //获取session之后获取用户强转ID
        User user =(User) ((WebEngineContext) context).getRequest().getSession().getAttribute("user");
        String userID = user.getUserID();
        //从redis中获取用户的菜单
        List<Menu> list =(List<Menu>) redisTemplate.opsForValue().get(userID);
        //获取按钮中权限的menucode
        String value = elementTag.getAttributeValue(attributeName);
        for (Menu m : list) {
            //如果有则显示，否则隐藏按钮
            if(m.getMenuCode().equals(value)){
                return true;
            }
        }
        return false;
    }
}
