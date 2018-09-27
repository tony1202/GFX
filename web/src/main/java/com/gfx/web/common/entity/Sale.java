package com.gfx.web.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sale")
public class Sale {
    /**
     * 出库记录id
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
     * 出库数量
     */
    @Column(name = "goods_number")
    private Integer goodsNumber;

    /**
     * 00:出货;01:样品
     */
    private String type;

    /**
     * 货物成色
     */
    @Column(name = "goods_quality")
    private String goodsQuality;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 业务员
     */
    private String salesman;

    /**
     * 客户
     */
    private String customer;

    /**
     * 备注
     */
    private String remark;

    /**
     * 出库时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取出库记录id
     *
     * @return record_id - 出库记录id
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * 设置出库记录id
     *
     * @param recordId 出库记录id
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
     * 获取出库数量
     *
     * @return goods_number - 出库数量
     */
    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    /**
     * 设置出库数量
     *
     * @param goodsNumber 出库数量
     */
    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    /**
     * 获取00:出货;01:样品
     *
     * @return type - 00:出货;01:样品
     */
    public String getType() {
        return type;
    }

    /**
     * 设置00:出货;01:样品
     *
     * @param type 00:出货;01:样品
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * 获取业务员
     *
     * @return salesman - 业务员
     */
    public String getSalesman() {
        return salesman;
    }

    /**
     * 设置业务员
     *
     * @param salesman 业务员
     */
    public void setSalesman(String salesman) {
        this.salesman = salesman == null ? null : salesman.trim();
    }

    /**
     * 获取客户
     *
     * @return customer - 客户
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * 设置客户
     *
     * @param customer 客户
     */
    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
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
     * 获取出库时间
     *
     * @return create_date - 出库时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置出库时间
     *
     * @param createDate 出库时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}