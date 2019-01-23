package com.architect.exception;

/**
 * @author wenxiong.jia
 * @since 2018/12/6
 */
public class TmsBusinessException extends BusinessException {
    private static final long serialVersionUID = -3460524052435971680L;

    public TmsBusinessException(Throwable t) {
        super(t);
    }

    public TmsBusinessException(Integer errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public TmsBusinessException(Integer errorCode, String errorMsg, Throwable cause) {
        super(errorCode, errorMsg, cause);
    }
}
