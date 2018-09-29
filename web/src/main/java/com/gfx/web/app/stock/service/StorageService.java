package com.gfx.web.app.stock.service;

import com.gfx.web.app.stock.dto.StoragePagination;
import com.gfx.web.common.entity.StockOperator;
import com.gfx.web.common.entity.Storage;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/27
 */
public interface StorageService {
    /**
     * 库存入库操作
     * @param stockOperator 入库操作
     */
    void addStorage(StockOperator stockOperator);

    /**
     * 通过ajax查询库存
     * @param goodsId 货物id
     * @param goodsType 货物类型
     * @param goodsQuality 货物成色
     * @return
     */
    List<Storage> getStorageAjax(String goodsId, String goodsType, String goodsQuality);

    /**
     * 出库出库操作
     * @param stockOut 出库操作
     */
    void reduceStorage(StockOperator stockOut);

    /**
     * 分页查询库存
     * @param pagination 分页条件
     * @return
     */
    Map<String,Object> getStorageList(StoragePagination pagination);
}
