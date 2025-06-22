package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.TypeId;

import java.lang.annotation.*;

/**
 * 表示哪个字段需要填充id
 * 默认填充雪花id
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface GenerateId {
    public TypeId value() default TypeId.SNOWFLAKE;
}
