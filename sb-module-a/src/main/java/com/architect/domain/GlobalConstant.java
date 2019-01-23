package com.architect.domain;

/**
 * @author wenxiong.jia
 * @since 2018/12/6
 */
public class GlobalConstant {
    public static final String PROJECT_NAME = "PROJECT_NAME";// 项目名称
    public static final String PREFIX = "prefix";
    public static final String SERVER_IP = "SERVER_IP";// 服务IP
    public static final String USER_IP = "USER_IP";// 用户IP
    public static final String TRACE_ID = "traceID";
    public static final String SYSTEM_CODE = "system.code";// 系统编码
    // 开放平台API 调用签名secret配置key
    public static final String OPEN_SECRET_CONFIG_KEY = "system.open.secret";
    /**
     * 错误码
     */
    public static final String ERROR_CODE = "errorCode";
    /**
     * 错误描述
     */
    public static final String ERROR_REASON = "errorReason";
    public static final String USE_TIME = "useTime";
    /**
     * 日志分隔符`|`
     */
    public static final String SEPARATOR = "|";
    /**
     * 日志格式拆分符号`:`
     */
    public static final String SPLIT = ":";
    /**
     * 请求URI
     */
    public static String requestURI = "requestURI";
}
