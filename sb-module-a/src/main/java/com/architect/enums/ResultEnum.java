package com.architect.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 返回结果枚举值
 *
 * @author linlg
 * @description 返回结果枚举值
 * @date 2017/11/6 19:39
 */

public enum ResultEnum {

    /**
     * 调用外部接口错误信息
     */
    FAILURE(9999, "FAILURE", "业务失败"),

    /**
     * 参数错误相关信息 4001
     */
    PARAM_ERROR(4001, "PARAM_ERROR", "参数错误");
    private int code;
    private String message;
    private String desc;

    ResultEnum(int code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    public static ResultEnum valueOf(int code) {
        ResultEnum[] enums = values();
        if (enums == null || enums.length == 0) {
            return FAILURE;
        }
        for (ResultEnum _enu : enums) {
            if (code == _enu.getCode()) {
                return _enu;
            }
        }

        return FAILURE;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        try {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("code", code)
                    .append("message", message)
                    .append("desc", desc)
                    .toString();
        } catch (Exception e) {
            // NOTICE: 这样做的目的是避免由于toString()的异常导致系统异常终止
            // 大部分情况下，toString()用在日志输出等调试场景
            return super.toString();
        }
    }
}
