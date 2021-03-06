package com.gfx.web.base.service.impl;

import com.gfx.web.base.dto.AccessDto;
import com.gfx.web.base.operate.UserOperation;
import com.gfx.web.base.operate.dto.SystemLogPagination;
import com.gfx.web.base.service.SystemLogService;
import com.gfx.web.common.dao.mapper.AccessRecordMapper;
import com.gfx.web.common.dao.mapper.UserMapper;
import com.gfx.web.common.entity.AccessRecord;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/6
 * @Description:
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {

    /**
     * 密码修改
     *
     * @param accessDto
     * @return
     */
    @Override
    @UserOperation(value = "修改密码")
    public String passwordModify(AccessDto accessDto) {
        String result;
        if (!StringUtils.equals(accessDto.getNewPassWord(), accessDto.getRePassWord())) {
            //新密码两次不一致
            result = "passwordUnmatched";
        } else {
            int num = userMapper.checkUserexists(accessDto.getUserId(), accessDto.getOldPassWord());
            if (num < 1) {
                //旧密码不对
                result = "passwordError";
            } else {
                //更新密码
                int i = userMapper.updatePassWord(accessDto.getUserId(), accessDto.getNewPassWord());
                if (i > 0) {
                    result = "ok";
                } else {
                    result = "updateError";
                }
            }
        }
        return result;
    }

    private final AccessRecordMapper accessRecordMapper;

    private final UserMapper userMapper;

    @Autowired
    public SystemLogServiceImpl(AccessRecordMapper accessRecordMapper, UserMapper userMapper) {
        this.accessRecordMapper = accessRecordMapper;
        this.userMapper = userMapper;
    }

    /**
     * 新增用户访问记录
     *
     * @param userId     用户id
     * @param accessIp   用户ip
     * @param accessType 访问类型
     */
    @Override
    public void addAccessRecord(String userId, String userName,String accessIp, String accessType) {
        AccessRecord accessRecord = new AccessRecord();
        accessRecord.setAccessDate(new Date());
        accessRecord.setAccessIp(accessIp);
        accessRecord.setAccessType(accessType);
        accessRecord.setUserName(userName);
        accessRecord.setUserId(userId);
        accessRecordMapper.insertSelective(accessRecord);
    }

    /**
     * 获取访问记录
     *
     * @param pagination 分类条件
     * @return
     */
    @Override
    public Map<String, Object> getAccessRecords(SystemLogPagination pagination) {

        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();

        if (StringUtils.isNotBlank(pagination.getUserName())){
            params.put("userName",pagination.getUserName());
        }
        if (!StringUtils.equalsIgnoreCase("all",pagination.getAccessType())){
            params.put("accessType",pagination.getAccessType());
        }
        if (pagination.getStartDate()!=null){
            params.put("startDate",pagination.getStartDate());
        }
        if (pagination.getEndDate()!=null){
            params.put("endDate",pagination.getEndDate());
        }
        Page<Object> page = PageHelper.startPage(pagination.getPageNum(), pagination.getLimit(), true);
        List<AccessRecord> list = accessRecordMapper.getAccessRecords(params);
        result.put("total",page.getTotal());
        result.put("data",list);
        return result;
    }
}
