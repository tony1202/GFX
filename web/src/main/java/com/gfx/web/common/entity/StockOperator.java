package com.gfx.web.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "stock_operator")
public class StockOperator {
    /**
     * 入库记录id
     */
    @Id
    @Column(name = "record_id")
    private String recordId;

    /**
     * 型号
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 货物类型
     */
    @Column(name = "goods_type")
    private String goodsType;

    /**
     * 入库类型:采购入库,生成入库,本厂维修入库,外发入库,出货类型:生成出库,本厂维修出库,外发出库,报废
     */
    @Column(name = "stock_type")
    private String stockType;

    /**
     * 货物尺寸
     */
    @Column(name = "goods_size")
    private String goodsSize;

    /**
     * 货物成色
     */
    @Column(name = "goods_quality")
    private String goodsQuality;

    /**
     * 入库数量
     */
    @Column(name = "goods_number")
    private Integer goodsNumber;

    /**
     * 库位id
     */
    @Column(name = "repository_id")
    private String repositoryId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 客户id
     */
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 业务员id
     */
    @Column(name = "sale_man")
    private String saleMan;

    /**
     * 备注
     */
    private String remark;

    /**
     * 出入库时间
     */
    @Column(name = "operator_date")
    private Date operatorDate;

    /**
     * 获取入库记录id
     *
     * @return record_id - 入库记录id
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * 设置入库记录id
     *
     * @param recordId 入库记录id
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    /**
     * 获取型号
     *
     * @return goods_id - 型号
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置型号
     *
     * @param goodsId 型号
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 获取货物类型
     *
     * @return goods_type - 货物类型
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * 设置货物类型
     *
     * @param goodsType 货物类型
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    /**
     * 获取入库类型:采购入库,生成入库,本厂维修入库,外发入库,出货类型:生成出库,本厂维修出库,外发出库,报废
     *
     * @return stock_type - 入库类型:采购入库,生成入库,本厂维修入库,外发入库,出货类型:生成出库,本厂维修出库,外发出库,报废
     */
    public String getStockType() {
        return stockType;
    }

    /**
     * 设置入库类型:采购入库,生成入库,本厂维修入库,外发入库,出货类型:生成出库,本厂维修出库,外发出库,报废
     *
     * @param stockType 入库类型:采购入库,生成入库,本厂维修入库,外发入库,出货类型:生成出库,本厂维修出库,外发出库,报废
     */
    public void setStockType(String stockType) {
        this.stockType = stockType == null ? null : stockType.trim();
    }

    /**
     * 获取货物尺寸
     *
     * @return goods_size - 货物尺寸
     */
    public String getGoodsSize() {
        return goodsSize;
    }

    /**
     * 设置货物尺寸
     *
     * @param goodsSize 货物尺寸
     */
    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize == null ? null : goodsSize.trim();
    }

    /**
     * 获取货物成色
     *
     * @return goods_quality - 货物成色
     */
    public String getGoodsQuality() {
        return goodsQuality;
    }

    /**
     * 设置货物成色
     *
     * @param goodsQuality 货物成色
     */
    public void setGoodsQuality(String goodsQuality) {
        this.goodsQuality = goodsQuality == null ? null : goodsQuality.trim();
    }

    /**
     * 获取入库数量
     *
     * @return goods_number - 入库数量
     */
    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    /**
     * 设置入库数量
     *
     * @param goodsNumber 入库数量
     */
    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    /**
     * 获取库位id
     *
     * @return repository_id - 库位id
     */
    public String getRepositoryId() {
        return repositoryId;
    }

    /**
     * 设置库位id
     *
     * @param repositoryId 库位id
     */
    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId == null ? null : repositoryId.trim();
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    /**
     * 获取客户id
     *
     * @return customer_id - 客户id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户id
     *
     * @param customerId 客户id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取业务员id
     *
     * @return sale_man - 业务员id
     */
    public String getSaleMan() {
        return saleMan;
    }

    /**
     * 设置业务员id
     *
     * @param saleMan 业务员id
     */
    public void setSaleMan(String saleMan) {
        this.saleMan = saleMan == null ? null : saleMan.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取出入库时间
     *
     * @return operator_date - 出入库时间
     */
    public Date getOperatorDate() {
        return operatorDate;
    }

    /**
     * 设置出入库时间
     *
     * @param operatorDate 出入库时间
     */
    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }
}