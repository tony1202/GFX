package com.gfx.web.app.baseData.service;

import com.gfx.web.base.dto.Pagination;
import com.gfx.web.common.entity.Customer;

import java.util.Map;

/**
 * @author tony
 * @date 2018/9/21
 */
public interface CustomerService {
    /**
     * 分页查询客户信息
     * @param pagination 分页条件
     * @param userId 用户id
     * @param isAdmin 是否admin
     * @return 分页数据 key:total - 总条数;key:data - 数据列
     */
    Map<String,Object> getCustomerList(Pagination pagination, String userId, String isAdmin);

    /**
     * 新客户
     * @param customer 客户
     * @return 新增结果
     */
    Boolean addCustomer(Customer customer);
}
