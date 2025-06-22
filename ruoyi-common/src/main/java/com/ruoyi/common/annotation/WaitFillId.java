package com.ruoyi.common.annotation;

import java.lang.annotation.*;


/**
 * 此注解表示了有哪些方法是需要填充id的
 * 这个填充是随机生成的id
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface WaitFillId {
}
