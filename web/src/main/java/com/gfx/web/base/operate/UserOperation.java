package com.gfx.web.base.operate;

import java.lang.annotation.*;

/**
 * 用于记录用户操作
 * @author tony
 * @date 2018/10/8
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserOperation {
    //指定相关操作的名称
    String value();
}
