package com.gfx.web.app.stock.service;

import com.gfx.web.common.entity.StockOperator;

/**
 * @author tony
 * @date 2018/9/27
 */
public interface StockRecordService {
    /**
     * 入库操作
     * @param stockIn 入库信息
     * @return 新增结果
     */
    boolean stockIn(StockOperator stockIn);
}
