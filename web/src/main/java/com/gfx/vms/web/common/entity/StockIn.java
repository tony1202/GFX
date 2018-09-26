package com.gfx.vms.web.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "stock_in")
public class StockIn {
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
     * 入库类型:采购入库,生成入库,本厂维修入库,外发入库,
     */
    @Column(name = "in_type")
    private String inType;

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
    private Integer repositoryId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 备注
     */
    private String remark;

    /**
     * 入库时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

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
     * 获取入库类型:采购入库,生成入库,本厂维修入库,外发入库,
     *
     * @return in_type - 入库类型:采购入库,生成入库,本厂维修入库,外发入库,
     */
    public String getInType() {
        return inType;
    }

    /**
     * 设置入库类型:采购入库,生成入库,本厂维修入库,外发入库,
     *
     * @param inType 入库类型:采购入库,生成入库,本厂维修入库,外发入库,
     */
    public void setInType(String inType) {
        this.inType = inType == null ? null : inType.trim();
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
    public Integer getRepositoryId() {
        return repositoryId;
    }

    /**
     * 设置库位id
     *
     * @param repositoryId 库位id
     */
    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
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
     * 获取入库时间
     *
     * @return create_date - 入库时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置入库时间
     *
     * @param createDate 入库时间
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