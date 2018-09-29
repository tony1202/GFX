package com.gfx.web.app.stock.service;

import com.gfx.web.app.stock.dto.StockRecordPagination;
import com.gfx.web.common.entity.StockOperator;

import java.util.Map;

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

    /**
     * 出库操作
     * @param stockOut 出库信息
     * @return
     */
    boolean stockOut(StockOperator stockOut);

    /**
     * 分页查询出入库记录
     * @param pagination 分页数据
     * @return key:total - 数量; key:data - 具体数据
     */
    Map<String,Object> searchStockRecord(StockRecordPagination pagination);
}
