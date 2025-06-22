package com.ruoyi.common.exception.attribute;

/**
 * 空属性值异常
 */
public class NullAttributeException extends RuntimeException {
    public NullAttributeException(String message) {
        super(message);
    }

    public NullAttributeException() {
        super();
    }
}
