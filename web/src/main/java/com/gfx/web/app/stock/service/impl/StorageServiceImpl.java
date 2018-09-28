package com.gfx.web.app.stock.service.impl;

import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.base.util.UUIDUtils;
import com.gfx.web.common.dao.mapper.StorageMapper;
import com.gfx.web.common.entity.StockOperator;
import com.gfx.web.common.entity.Storage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
     * @param stockOperator 出入库操作
     */
    @Override
    public void addStorage(StockOperator stockOperator) {
        //检查是否已经存响应的库存记录
        Storage storageOld = checkExist(stockOperator.getGoodsId(), stockOperator.getGoodsQuality(),stockOperator.getGoodsType());
        //如果存在相应的存储,并且库位一致
        if (storageOld!=null && StringUtils.equalsIgnoreCase(stockOperator.getRepositoryId(),storageOld.getRepositoryId())){
            storageOld.setCurrentNum(stockOperator.getGoodsNumber()+storageOld.getCurrentNum());
            //如果本次入库采购
            storageOld.setInitNum(stockOperator.getGoodsNumber()+storageOld.getInitNum());
            storageOld.setUpdateDate(new Date());
            storageMapper.updateByPrimaryKeySelective(storageOld);
        }else {
            Storage storage = new Storage();
            //复制属性
            BeanUtils.copyProperties(stockOperator,storage);
            //生成id
           storage.setId(UUIDUtils.uuid("S"));
           storage.setUpdateDate(new Date());
           storage.setCreateDate(new Date());
           storage.setInitNum(stockOperator.getGoodsNumber());
           storage.setCurrentNum(stockOperator.getGoodsNumber());
           storageMapper.insertSelective(storage);
        }
    }

    /**
     * 通过ajax查询库存
     *
     * @param goodsId      货物id
     * @param goodsType    货物类型
     * @param goodsQuality 货物成色
     * @return
     */
    @Override
    public List<Storage> getStorageAjax(String goodsId, String goodsType, String goodsQuality) {
        return storageMapper.getStorageAjax(goodsId,goodsType,goodsQuality);
    }

    /**
     * 查询获取库存情况
     * @param goodsId 货物id
     * @param goodsQuality 货物成色
     * @param goodsType 货物类型
     * @return
     */
    private Storage checkExist(String goodsId,String goodsQuality,String goodsType) {
        List<Storage> storages = storageMapper.checkExist(goodsId, goodsQuality,goodsType);
        if (storages.size() > 0) {
            return storages.get(0);
        }
        return null;
    }
}
