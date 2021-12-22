package com.service_base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 刘佳俊
 *
 * Mybatis-plus
 *
 * mp中关于自动填充的配置类
 *
 * 1. 对应实体类中需要自动填充的字段需要添加注解 @TableField(fill = FieldFill.INSERT_UPDATE)
 *
 * 2. 创建一个类继承MetaObjectHandler并实现里面的方法
 *
 *
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 插入时自动填充
    @Override
    public void insertFill(MetaObject metaObject) {

        // 注意这里写的是属性名，自动填充的值，metaobject
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    // 更新时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
