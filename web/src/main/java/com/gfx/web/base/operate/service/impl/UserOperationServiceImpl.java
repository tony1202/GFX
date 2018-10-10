package com.gfx.web.base.operate.service.impl;

import com.gfx.web.base.operate.dto.SystemLogPagination;
import com.gfx.web.base.operate.service.UserOperationService;
import com.gfx.web.common.dao.mapper.OperationRecordMapper;
import com.gfx.web.common.entity.OperationRecord;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/10/8
 */
@Service
public class UserOperationServiceImpl implements UserOperationService {
    private static final Logger log = LoggerFactory.getLogger(UserOperationServiceImpl.class);

    private final OperationRecordMapper operationRecordMapper;

    @Autowired
    public UserOperationServiceImpl(OperationRecordMapper operationRecordMapper) {
        this.operationRecordMapper = operationRecordMapper;
    }

    /**
     * 新增用户操作记录
     *
     * @param operationRecord 用户操作记录
     */
    @Override
    public void addOperationRecord(OperationRecord operationRecord) {
        operationRecordMapper.insertSelective(operationRecord);
    }

    /**
     * 分页查询操作日志
     *
     * @param pagination 查询条件
     * @return key:data - 数据; key:total - 记录条数
     */
    @Override
    public Map<String, Object> getUserOperationRecords(SystemLogPagination pagination) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        if (pagination.getLimit()<0||pagination.getOffset()<0){
            log.warn("分页条件有误");
            return null;
        }
        if (StringUtils.isNotBlank(pagination.getUserName())){
            params.put("userName",pagination.getUserName());
        }
        if (pagination.getStartDate()!=null){
            params.put("startDate",pagination.getStartDate());
        }
        if (pagination.getEndDate()!=null){
            params.put("endDate",pagination.getEndDate());
        }
        Page<Object> page = PageHelper.startPage(pagination.getPageNum(), pagination.getLimit(), true);
        List<OperationRecord> list = operationRecordMapper.getUserOperationRecords(params);
        result.put("data",list);
        result.put("total",page.getTotal());
        return result;
    }
}
