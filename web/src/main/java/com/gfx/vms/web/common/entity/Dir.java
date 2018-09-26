package com.gfx.vms.web.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dir")
public class Dir {
    /**
     * 字典id
     */
    @Id
    private String id;

    /**
     * 字典分类id
     */
    @Id
    private String cid;

    /**
     * 字典名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 获取字典id
     *
     * @return id - 字典id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置字典id
     *
     * @param id 字典id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取字典分类id
     *
     * @return cid - 字典分类id
     */
    public String getCid() {
        return cid;
    }

    /**
     * 设置字典分类id
     *
     * @param cid 字典分类id
     */
    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    /**
     * 获取字典名
     *
     * @return NAME - 字典名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置字典名
     *
     * @param name 字典名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
}