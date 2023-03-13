package com.springboot.custom;

import com.springboot.pojo.Menu;
import com.springboot.pojo.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.List;

/**
 * 自定义标签
 */
public class MenuIterator extends AbstractElementTagProcessor {

    private RedisTemplate<Object,Object> redisTemplate;

    public MenuIterator(RedisTemplate<Object,Object> redisTemplate) {
        super(
                //此处理器将仅应用于Html，.后缀是什么就应用于什么
                TemplateMode.HTML,
                //要应用于标签名称的前缀<bs:>
                "bs",
                //标签名称：匹配此名称的特定标签<bs:test>
                "test",
                //标签前缀是否应用于标签名称
                true,
                // 无属性名称：将通过标签名称匹配
                null,
                // 没有要应用于属性名称的前缀
                false,
                //优先(内部方言自己的优先)
                1000);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag elementTag, IElementTagStructureHandler handler) {
        //获取redis里全量有序的菜单
        List<Menu> list =(List<Menu>) redisTemplate.opsForValue().get("menuList");
        //获取session对象在获取用户信息
        User user =(User) ((WebEngineContext) context).getRequest().getSession().getAttribute("user");
        //获取redis在用户中的的权限
        List<Menu> uList =(List<Menu>) redisTemplate.opsForValue().get(user.getUserID());
        //获取模型
        IModelFactory iModelFactory = context.getModelFactory();
        //创建模型
        IModel model = iModelFactory.createModel();
        System.out.println(list.toString());
        System.out.println(uList.toString());
        model.add(iModelFactory.createOpenElementTag("ul"));//<ul> 外层max开始
        for (Menu m1 : list) {
            if(!uList.contains(m1))
                continue;
            model.add(iModelFactory.createOpenElementTag("li"));//<li>一级开始
            model.add(iModelFactory.createText(m1.getMenuName()));//一级菜单
            model.add(iModelFactory.createOpenElementTag("ul"));//<ul>二级开始
            for (Menu m2:m1.getMenuList()){
                if (!uList.contains(m2))
                    continue;
                model.add(iModelFactory.createOpenElementTag("li"));//<li>
                model.add(iModelFactory.createText(m2.getMenuName()));//二级菜单
                model.add(iModelFactory.createOpenElementTag("ul"));//<ul>三级开始
                for(Menu m3:m2.getMenuList()){
                    if (!uList.contains(m3))
                        continue;
                    model.add(iModelFactory.createOpenElementTag("li"));//<li>
                    model.add(iModelFactory.createText(m3.getMenuName()));//三级菜单
                    model.add(iModelFactory.createCloseElementTag("li"));//</li>
                }
                model.add(iModelFactory.createCloseElementTag("ul"));//</ul>三级结束
                model.add(iModelFactory.createCloseElementTag("li"));//</li>
            }
            model.add(iModelFactory.createCloseElementTag("ul"));//</ul>二级结束
            model.add(iModelFactory.createCloseElementTag("li"));//</li>一级结束
        }
        model.add(iModelFactory.createCloseElementTag("ul"));//</ul>外层max结束
        handler.replaceWith(model,false);
    }
}
