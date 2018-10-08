package com.gfx.web.base.operate;

import com.gfx.web.base.context.UserContextHolder;
import com.gfx.web.base.dto.UserInfoDto;
import com.gfx.web.base.operate.service.UserOperationService;
import com.gfx.web.common.entity.OperationRecord;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author tony
 * @date 2018/10/8
 */
@Component
@Aspect
public class UserOperationLogging {
    @Autowired
    private UserOperationService userOperationService;

    /**
     * 切点
     */
    @Pointcut(value = "@annotation(com.gfx.web.base.operate.UserOperation)")
    public void operation() {
    }

    /**
     * 记录用户操作的指定操作的行为
     *
     * @param joinPoint     切入点
     * @param returnValue   返回值
     * @param userOperation 注解
     */
    @AfterReturning(returning = "returnValue", value = "operation() && @annotation(userOperation)")
    public void loggingUserOperation(JoinPoint joinPoint, Object returnValue, UserOperation userOperation) {
        System.out.println("return");
        if (userOperation != null) {
            //获取操作名
            String operationName = userOperation.value();
            //获取调用的方法名
            String methodName = joinPoint.getSignature().getName();

            //操作结果
            String operationResult = "-";
            if (returnValue instanceof Boolean) {
                Boolean res = (Boolean) returnValue;
                operationResult = res ? "成功" : "失败";
            } else if (returnValue instanceof String) {
                String res = (String) returnValue;
                operationResult = StringUtils.equalsIgnoreCase("ok", res) ? "成功" : "失败";
            }

            //获取用户信息
            UserInfoDto userInfo = UserContextHolder.getUserInfo();
            OperationRecord operationRecord = new OperationRecord();
            operationRecord.setUserId(userInfo.getUserId());
            operationRecord.setUserName(userInfo.getUserName());
            operationRecord.setOperatorName(operationName);
            operationRecord.setOperatorResult(operationResult);
            operationRecord.setOperatorDate(new Date());

            userOperationService.addOperationRecord(operationRecord);
        }

    }

}
