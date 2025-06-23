package com.ruoyi.common.annotation;

/**
 * 自定义异常抛出接口
 * 其是作为传递方法使用的
 * @param <T>
 */
@FunctionalInterface
public interface SelfDefineException<T> {
    void accept(T arg) throws Exception;

}
