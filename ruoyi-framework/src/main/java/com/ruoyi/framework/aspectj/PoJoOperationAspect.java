package com.ruoyi.framework.aspectj;

import com.ruoyi.common.annotation.GenerateId;
import com.ruoyi.common.annotation.SelfDefineException;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

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
     * 以下两个是添加时间的切面的点方法, 为了美观以及方便管理, 故抽取放在这里
     */
    @Pointcut("@annotation(com.ruoyi.common.annotation.DateOperation)")
    public void addDatePointcutOne() {}

    @Pointcut("execution(* com.remember..service.impl.*.update*(..))")
    public void addDatePointcutTwo() {}

    /**
     * 添加生成时间
     * todo 后期需要优化, 不能写死的,
     * @param arg 等待添加的对象
     */
    private void addGenerateDate(Object arg) throws NoSuchFieldException, IllegalAccessException {
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

    /**
     * 添加生成 Id 的方法
     * @param arg 等待添加的对象
     */
    private void addGenerateId(Object arg) throws InvocationTargetException, IllegalAccessException {
        for (Field field : arg.getClass().getDeclaredFields()) {
            GenerateId annotation = field.getAnnotation(GenerateId.class);
            if (annotation == null) {
                continue;
            }
            field.setAccessible(true);
            field.set(arg, annotation.value().execution());
            break;
        }
    }

    /**
     * 执行中间件
     * @param joinPoint 操作的点对象
     * @param function 执行的方法
     * @throws NoSuchFieldException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void moderExecution(JoinPoint joinPoint, SelfDefineException<Object> function) throws Exception {
        for (Object arg : joinPoint.getArgs()) {
            Class<?> argClass = arg.getClass();
            if (List.class.isAssignableFrom(argClass)) {
                List<?> objects = (List<?>) arg;
                for (Object object : objects) {
                    function.accept(object);
                }
            } else {
                function.accept(arg);
            }
        }
    }

    /**
     * 填充id的注解, 需要搭配 WaitFillId 和 GenerateId 注解使用
     * 或者是规范的命名
     * @param joinPoint
     */
    @Before("generateIdPointcutOne() || generateIdPointcutTwo()")
    public void generate_id(JoinPoint joinPoint) throws Exception {
        moderExecution(joinPoint, this::addGenerateId);
    }

    /**
     * 填充时间的注解, 插入的时候也需要添加
     * @param joinPoint
     */
    @Before("addDatePointcutOne() || addDatePointcutTwo() || generateIdPointcutOne() || generateIdPointcutTwo()")
    public void fill_date(JoinPoint joinPoint) throws Exception {
        moderExecution(joinPoint, this::addGenerateDate);
    }
}
