package com.gfx.web.base.operate.controller;

import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.base.operate.dto.SystemLogPagination;
import com.gfx.web.base.operate.service.UserOperationService;
import com.gfx.web.base.service.SystemLogService;
import com.gfx.web.common.entity.AccessRecord;
import com.gfx.web.common.entity.OperationRecord;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/10/8
 */
@RestController
@RequestMapping("/systemLog")
public class SystemLogController {
    private static final Logger log = LoggerFactory.getLogger(SystemLogController.class);
    private UserOperationService userOperationService;
    private final SystemLogService systemLogService;

    @Autowired
    public SystemLogController(UserOperationService userOperationService, SystemLogService systemLogService) {
        this.userOperationService = userOperationService;
        this.systemLogService = systemLogService;
    }

    @GetMapping("/getUserOperationRecords")
    public Map<String,Object> getUserOperationRecords(SystemLogPagination pagination){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            Map<String,Object> map = userOperationService.getUserOperationRecords(pagination);
            if (MapUtils.isNotEmpty(map)){
                vmsResponse.setResponseBodyTotal((Long) map.get("total"));
                vmsResponse.setCustomerInfo("rows",(List<OperationRecord>)map.get("data"));
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("getUserOperationRecords is error ->",e);
        }
        return vmsResponse.generateResponseBody();
    }

    @GetMapping("/getAccessRecords")
    public Map<String,Object> getAccessRecords(SystemLogPagination pagination){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            Map<String,Object> map = systemLogService.getAccessRecords(pagination);
            if (MapUtils.isNotEmpty(map)){
                vmsResponse.setResponseBodyTotal((Long) map.get("total"));
                vmsResponse.setCustomerInfo("rows",(List<AccessRecord>)map.get("data"));
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("getAccessRecords is error ->",e);
        }
        return vmsResponse.generateResponseBody();
    }
}
