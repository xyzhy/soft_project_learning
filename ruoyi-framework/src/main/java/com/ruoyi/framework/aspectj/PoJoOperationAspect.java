package com.ruoyi.framework.aspectj;

import com.ruoyi.common.annotation.GenerateId;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 此切面主要应用于PoJo类的切面
 * 专门用来对其中的字段进行填充等操作的
 * 现如今已有功能: [填充id, 填充时间]
 */
@Aspect
@Component
public class PoJoOperationAspect {
    private final static Logger logger = LoggerFactory.getLogger(PoJoOperationAspect.class);

    /**
     * 以下两个是生成id切面的点方法, 为了美观以及方便管理, 故抽取放在这里
     */
    @Pointcut("@annotation(com.ruoyi.common.annotation.WaitFillId)")
    public void generateIdPointcutOne() {}

    @Pointcut("execution(* com.remember..service.impl.*.insert*(..))")
    public void generateIdPointcutTwo() {}

    /**
     * 填充id的注解, 需要搭配 WaitFillId 和 GenerateId 注解使用
     * 或者是规范的命名
     * @param joinPoint
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Before("generateIdPointcutOne() || generateIdPointcutTwo()")
    public void generate_id(JoinPoint joinPoint) throws IllegalAccessException, InvocationTargetException {
        logger.info("----------------------------------------------------------");
        
        for (Object arg : joinPoint.getArgs()) {
            for (Field field : arg.getClass().getDeclaredFields()) {
                GenerateId annotation = field.getAnnotation(GenerateId.class);
                if (annotation == null) {
                    continue;
                }
                field.setAccessible(true);
                field.set(arg, annotation.value().execution());
            }
        }

        logger.info("enter id success");
    }

    /**
     * 填充时间的注解
     * todo 后期需要优化, 不能写死的,
     * @param joinPoint
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    @Before("@annotation(com.ruoyi.common.annotation.DateOperation)")
    public void fill_date(JoinPoint joinPoint) throws IllegalAccessException, NoSuchFieldException {
        for (Object arg : joinPoint.getArgs()) {
            Field updateDate = arg.getClass().getDeclaredField("updateDate");
            Field createDate = arg.getClass().getDeclaredField("createDate");

            updateDate.setAccessible(true);
            createDate.setAccessible(true);

            Date date = new Date();
            updateDate.set(arg, date);
            if (createDate.get(arg) == null) {
                createDate.set(arg, date);
            }
        }
    }
}
