package com.architect.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QrCodeInfo implements Serializable {
    /**
     * 唯一表示（二维码后半部分）
     */
    private String qrCode;

    /**
     * 二维码网址
     */
    private String qrCodeUrl;

    /**
     * 二维码状态
     */
    private Integer status;

    /**
     * 供应商no
     */
    private String supplierNo;

    /**
     * 物品编码
     */
    private String goodsCode;

    /**
     * 物品名称
     */
    private String goodsName;

    /**
     * 生产厂名称
     */
    private String productionPlant;

    /**
     * 生产时间
     */
    private Date productionTime;

    /**
     * 批次号
     */
    private String batchNumber;

    /**
     * 物业地址
     */
    private String ratingAddress;

    /**
     * 房源编号
     */
    private String houseCode;

    /**
     * 配置订单
     */
    private String orderCode;

    /**
     * 装修供应商
     */
    private String decorationSupplierNo;

    /**
     * 进场时间
     */
    private Date arrivalTime;

    /**
     * 失败原因，数据库不保存该字段
     */
    private String errorInfoDesc;

    private static final long serialVersionUID = 1L;
}