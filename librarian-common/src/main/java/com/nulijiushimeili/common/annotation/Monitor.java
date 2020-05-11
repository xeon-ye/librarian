package com.nulijiushimeili.common.annotation;


import java.lang.annotation.*;


/**
 * 使用这个当前注解监控控制层的输入输出
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Monitor {

}
