package com.gfx.vms.web.common.entity;

import javax.persistence.*;

@Table(name = "goods")
public class Goods {
    /**
     * 型号
     */
    @Id
    private String id;

    /**
     * 货物类型
     */
    @Id
    @Column(name = "TYPE")
    private String type;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 尺寸
     */
    private String size;

    /**
     * 货物价值
     */
    @Column(name = "VALUE")
    private Double value;

    /**
     * 获取型号
     *
     * @return id - 型号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置型号
     *
     * @param id 型号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 获取品牌
     *
     * @return brand - 品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌
     *
     * @param brand 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    /**
     * 获取尺寸
     *
     * @return size - 尺寸
     */
    public String getSize() {
        return size;
    }

    /**
     * 设置尺寸
     *
     * @param size 尺寸
     */
    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    /**
     * 获取货物价值
     *
     * @return VALUE - 货物价值
     */
    public Double getValue() {
        return value;
    }

    /**
     * 设置货物价值
     *
     * @param value 货物价值
     */
    public void setValue(Double value) {
        this.value = value;
    }
}