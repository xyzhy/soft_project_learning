package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * 添加了此注解的会自动寻找时间类型的字段进行填充
 * 包含create只在不为空时填充
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DateOperation {
}
