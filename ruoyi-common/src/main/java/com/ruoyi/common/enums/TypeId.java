package com.ruoyi.common.enums;

import com.ruoyi.common.utils.id.SnowflakeId;
import com.ruoyi.common.utils.uuid.IdUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 枚举 id 类
 * 选项你需要使用的对象
 * 然后调用枚举类的execution方法, 就可以获取对应的id
 */
public enum TypeId {
    /**
     * 雪花id
     */
    SNOWFLAKE(SnowflakeId.class, "getNextId"),
    /**
     * UUID
     */
    UUID(IdUtils.class, "fastSimpleUUID");

    private Method method;
    /**
     * 生成id类别
     *
     * @param clazz
     * @param methodName
     */
    TypeId(Class<?> clazz, String methodName) {
        try {
            this.method = clazz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到id
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String execution() throws InvocationTargetException, IllegalAccessException {
        return (String) method.invoke(null);
    }
}
