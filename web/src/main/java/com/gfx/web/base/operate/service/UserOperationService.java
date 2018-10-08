package com.gfx.web.base.operate.service;

import com.gfx.web.base.operate.dto.SystemLogPagination;
import com.gfx.web.common.entity.OperationRecord;

import java.util.Map;

/**
 * @author tony
 * @date 2018/10/8
 */
public interface UserOperationService {
    /**
     * 新增用户操作记录
     * @param operationRecord 用户操作记录
     */
    void addOperationRecord(OperationRecord operationRecord);

    /**
     * 分页查询操作日志
     * @param pagination 查询条件
     * @return key:data - 数据; key:total - 记录条数
     */
    Map<String,Object> getUserOperationRecords(SystemLogPagination pagination);
}
