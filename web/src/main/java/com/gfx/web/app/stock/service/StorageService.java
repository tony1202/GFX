package com.gfx.web.app.stock.service;

import com.gfx.web.app.stock.dto.StorageDto;
import com.gfx.web.app.stock.dto.StoragePagination;
import com.gfx.web.common.entity.StockOperator;
import com.gfx.web.common.entity.Storage;

import java.io.File;
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

    /**
     * 导出Excel
     * @param pagination 导出数据条件
     * @param storageDtoClass 数据源
     * @return 文件
     */
    File exportStorageRecord(StoragePagination pagination, Class<StorageDto> storageDtoClass);

    /**
     * 跟新库存
     * @param storage 库存信息
     * @return
     */
    boolean updateStorage(Storage storage);

    /**
     * 删除库存
     * @param storageId 库存id
     * @return
     */
    boolean deleteStorage(String storageId);
}
