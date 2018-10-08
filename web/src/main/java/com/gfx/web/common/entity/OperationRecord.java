package com.gfx.web.common.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "operation_record")
public class OperationRecord {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 操作名称
     */
    @Column(name = "operation_name")
    private String operationName;

    /**
     * 操作结果
     */
    @Column(name = "operation_result")
    private String operationResult;

    /**
     * 操作时间
     */
    @Column(name = "operation_date")
    private Date operationDate;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取操作名称
     *
     * @return operator_name - 操作名称
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * 设置操作名称
     *
     * @param operationName 操作名称
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    /**
     * 获取操作结果
     *
     * @return operation_result - 操作结果
     */
    public String getOperationResult() {
        return operationResult;
    }

    /**
     * 设置操作结果
     *
     * @param operationResult 操作结果
     */
    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult == null ? null : operationResult.trim();
    }

    /**
     * 获取操作时间
     *
     * @return operation_date - 操作时间
     */
    public Date getOperationDate() {
        return operationDate;
    }

    /**
     * 设置操作时间
     *
     * @param operationDate 操作时间
     */
    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}