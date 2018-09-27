package com.gfx.web.app.stock.service;

import com.gfx.web.common.entity.Storage; /**
 * @author tony
 * @date 2018/9/27
 */
public interface StorageService {
    /**
     * 库存入库操作
     * @param storage 库存
     */
    void addStorage(Storage storage);
}
