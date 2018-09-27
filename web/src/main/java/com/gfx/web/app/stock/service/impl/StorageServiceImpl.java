package com.gfx.web.app.stock.service.impl;

import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.common.dao.mapper.StorageMapper;
import com.gfx.web.common.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tony
 * @date 2018/9/27
 */
@Service
public class StorageServiceImpl implements StorageService {

    private StorageMapper storageMapper;

    @Autowired
    public StorageServiceImpl(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }

    /**
     * 库存入库操作
     *
     * @param storage 库存
     */
    @Override
    public void addStorage(Storage storage) {
        boolean res = checkExist(storage);
        if (res){//已经存在
            storageMapper.updateStorage(storage);
        }
    }

    private boolean checkExist(Storage storage){
        if (storage!=null){

            int num = storageMapper.checkExist(storage.getGoodsId(),storage.getGoodsQuality());
            return num>0;
        }
        return true;
    }
}
