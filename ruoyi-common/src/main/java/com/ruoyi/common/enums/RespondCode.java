package com.ruoyi.common.enums;

/**
 * 响应码枚举类
 */
public enum RespondCode {
    /**
     * 需要错误通知, 但是正常返回数据
     */
    NOTIFICATION_AND_NORMAL_RETURNED_DATA(702);

    private int code;

    RespondCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
