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
    @Column(name = "model_num")
    private String modelNum;

    /**
     * 货物类型
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 货物成色
     */
    private String quality;

    /**
     * 货物尺寸
     */
    private String size;

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
    private Integer repositoryId;

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
     * @return model_num - 型号
     */
    public String getModelNum() {
        return modelNum;
    }

    /**
     * 设置型号
     *
     * @param modelNum 型号
     */
    public void setModelNum(String modelNum) {
        this.modelNum = modelNum == null ? null : modelNum.trim();
    }

    /**
     * 获取货物类型
     *
     * @return TYPE - 货物类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置货物类型
     *
     * @param type 货物类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取货物成色
     *
     * @return quality - 货物成色
     */
    public String getQuality() {
        return quality;
    }

    /**
     * 设置货物成色
     *
     * @param quality 货物成色
     */
    public void setQuality(String quality) {
        this.quality = quality == null ? null : quality.trim();
    }

    /**
     * 获取货物尺寸
     *
     * @return size - 货物尺寸
     */
    public String getSize() {
        return size;
    }

    /**
     * 设置货物尺寸
     *
     * @param size 货物尺寸
     */
    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
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
    public Integer getRepositoryId() {
        return repositoryId;
    }

    /**
     * 设置仓库id
     *
     * @param repositoryId 仓库id
     */
    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
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