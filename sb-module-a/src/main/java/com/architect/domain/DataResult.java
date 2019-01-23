package com.architect.domain;

import com.architect.exception.ErrorCode;
import com.architect.exception.StatusDefinition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用RPC数据序列化传递对象
 *
 * @author SHOUSHEN LUAN
 */
public class DataResult<T> extends Result{

    private static final long serialVersionUID = 3335774490588365954L;
    /**
     * 解析协议状态码
     */
    @JsonProperty("status")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Status status = new Status(0, "");
    /**
     * 返回Result
     */
    @JsonProperty("result")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;
    /**
     * 附加属性/扩展属性
     */
    @JsonProperty("attachment")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> attachment;

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static DataResult<Object> success(Object result) {
        DataResult<Object> dataResult = new DataResult<Object>();
        dataResult.setResult(result);
        return dataResult;
    }

    /**
     * 部分成功
     *
     * @param result
     * @return
     */
    public static DataResult<Object> part(Object result) {
        DataResult<Object> dataResult = new DataResult<Object>();
        dataResult.setResult(result);
        dataResult.status.setStatusCode(ErrorCode.PART_ERROR.getErrorCode(), true);
        dataResult.status.setStatusReason(ErrorCode.PART_ERROR.getErrorReason());
        return dataResult;
    }

    /**
     * 创建部分成功的结构体
     *
     * @param key
     * @param value
     * @return
     */
    public static DataResult<Map<String, Object>> makePart(String key, Object value) {
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        dataResult.setResult(new HashMap<>());
        dataResult.result.put(key, value);
        dataResult.status.setStatusCode(ErrorCode.PART_ERROR.getErrorCode(), true);
        dataResult.status.setStatusReason(ErrorCode.PART_ERROR.getErrorReason());
        return dataResult;
    }

    @SuppressWarnings("unchecked")
    public static <T> DataResult<T> success(Object result, Class<T> type) {
        DataResult<T> dataResult = new DataResult<T>();
        dataResult.setResult((T) result);
        return dataResult;
    }

    public static <T> DataResult<T> faild(int errorCode, String errMsg) {
        DataResult<T> dataResult = new DataResult<T>();
        dataResult.setStatus(new Status(errorCode, errMsg));
        return dataResult;
    }

    /**
     * 当RPC调用失败时，使用该方式包装处理，该处理方式不会appeng当前系统编码
     *
     * @param errorCode
     * @param errMsg
     * @return
     */
    public static <T> DataResult<T> rpcFaild(int errorCode, String errMsg) {
        DataResult<T> dataResult = new DataResult<T>();
        dataResult.setStatus(new Status(errorCode, errMsg, false));
        return dataResult;
    }

    /**
     * 返回的状态码自动最加当前系统编码
     *
     * @param statusDefinition
     * @return
     */
    public static <T> DataResult<T> faild(StatusDefinition statusDefinition) {
        DataResult<T> dataResult = new DataResult<T>();
        dataResult.setStatus(new Status(statusDefinition.getErrorCode(), statusDefinition.getErrorReason()));
        return dataResult;
    }

    public static DataResult<Map<String, Object>> make(String key, Object value) {
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        dataResult.setResult(new HashMap<>());
        dataResult.result.put(key, value);
        return dataResult;
    }

    @SuppressWarnings("unchecked")
    public DataResult<Map<String, Object>> addResult(String key, Object value) {
        Map<String, Object> result = (Map<String, Object>) this.getResult();
        result.put(key, value);
        return (DataResult<Map<String, Object>>) this;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return getStatus().getStatusCode() == 0;
    }

    /**
     * 接口返回错误
     */
    @JsonIgnore
    public boolean isFailed() {
        return !isSuccess();
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void addAttachment(String key, Object value) {
        if (attachment == null) {
            this.attachment = new HashMap<>();
        }
        this.attachment.put(key, value);
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    /**
     * 默认构造器
     */
    public DataResult(){
//        RequestTrace.appendTrace(this);
    }
}
