package com.gfx.web.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "storage")
public class Storage {
    /**
     * 库存主键
     */
    @Id
    private String id;

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
     * 货物成色
     */
    @Column(name = "goods_quality")
    private String goodsQuality;

    /**
     * 货物尺寸
     */
    @Column(name = "goods_size")
    private String goodsSize;

    /**
     * 当前库存
     */
    @Column(name = "current_num")
    private Integer currentNum;

    /**
     * 初始库存
     */
    @Column(name = "init_num")
    private Integer initNum;

    /**
     * 仓库id
     */
    @Column(name = "repository_id")
    private String repositoryId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取库存主键
     *
     * @return id - 库存主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置库存主键
     *
     * @param id 库存主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 获取当前库存
     *
     * @return current_num - 当前库存
     */
    public Integer getCurrentNum() {
        return currentNum;
    }

    /**
     * 设置当前库存
     *
     * @param currentNum 当前库存
     */
    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    /**
     * 获取初始库存
     *
     * @return init_num - 初始库存
     */
    public Integer getInitNum() {
        return initNum;
    }

    /**
     * 设置初始库存
     *
     * @param initNum 初始库存
     */
    public void setInitNum(Integer initNum) {
        this.initNum = initNum;
    }

    /**
     * 获取仓库id
     *
     * @return repository_id - 仓库id
     */
    public String getRepositoryId() {
        return repositoryId;
    }

    /**
     * 设置仓库id
     *
     * @param repositoryId 仓库id
     */
    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId == null ? null : repositoryId.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}