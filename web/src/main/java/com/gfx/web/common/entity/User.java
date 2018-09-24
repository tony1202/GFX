package com.gfx.web.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User {
    /**
     * 用户主键
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 用姓名名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "pass_word")
    private String passWord;

    /**
     * 用户状态：0-正常；1-禁用
     */
    @Column(name = "user_status")
    private String userStatus;

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
     * 获取用户主键
     *
     * @return user_id - 用户主键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户主键
     *
     * @param userId 用户主键
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取用姓名名
     *
     * @return user_name - 用姓名名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用姓名名
     *
     * @param userName 用姓名名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取密码
     *
     * @return pass_word - 密码
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置密码
     *
     * @param passWord 密码
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    /**
     * 获取用户状态：0-正常；1-禁用
     *
     * @return user_status - 用户状态：0-正常；1-禁用
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态：0-正常；1-禁用
     *
     * @param userStatus 用户状态：0-正常；1-禁用
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
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