package com.architect.domain;

import com.architect.exception.ErrorCode;
import com.architect.exception.RpcException;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import java.io.Serializable;

abstract class Result implements Serializable {

    private static final long serialVersionUID = 610503329211184167L;

    public abstract Status getStatus();

    @Deprecated
    public void throwOpenException() {
        throwRpcException();
    }

    public void throwRpcException() {
        if (getStatus().getStatusCode() != 0) {
            throw new RpcException(getStatus().getStatusCode(), getStatus().getStatusReason());
        }
    }

    /**
     * 断言RPC OK 全部成功或部分成功
     */
    public void assertRpcOK() {

        if (getStatus().getStatusCode() != 0 && getStatus().getStatusCode() != ErrorCode.PART_ERROR.getErrorCode()) {
            throwRpcException();
        }
    }

    /**
     * 断言RPC调用全部成功
     */
    public void assertRpcAllSuccess() {
        if (getStatus().getStatusCode() != 0) {
            throwRpcException();
        }
    }

    private static final XStream xstream = new XStream();
    static {// 暂时使用默认方式（与Spring restful保持统一）
        // NameCoder nameCoder = new XStream11NameCoder() {
        // @Override
        // public String encodeNode(String name) {
        // int startIndex = -1;
        // if (name.indexOf(".") != -1) {
        // startIndex = name.lastIndexOf(".");
        // }
        // if (name.indexOf("$") != -1) {
        // int endIndex = name.lastIndexOf("$");
        // startIndex = Math.max(startIndex, endIndex);
        // }
        // if (startIndex != -1) {
        // name = name.substring(startIndex + 1);
        // return name;
        // }
        // return super.encodeNode(name);
        // }
        // };
        // HierarchicalStreamDriver driver = new XppDriver(nameCoder);
        // xstream = new XStream(driver);
        // xstream.aliasSystemAttribute(null, "class");// 删除属性
        xstream.alias("DataResult", DataResult.class);
    }
    private static final Gson gson = new Gson();

    public String toXML() {
        return xstream.toXML(this);
    }

    public String toJSON() {
        return gson.toJson(this);
    }
}
