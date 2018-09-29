package com.gfx.web.app.stock.service.impl;

import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.base.exception.StorageException;
import com.gfx.web.base.util.UUIDUtils;
import com.gfx.web.common.dao.mapper.StorageMapper;
import com.gfx.web.common.entity.StockOperator;
import com.gfx.web.common.entity.Storage;
import com.mchange.lang.IntegerUtils;
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
     * @param stockOperator 入库操作
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
        return storageMapper.getStorageAjax(goodsId,goodsType,goodsQuality,null);
    }

    /**
     * 出库出库操作
     *
     * @param stockOut 出库操作
     */
    @Override
    public void reduceStorage(StockOperator stockOut) {
        //查询当前库存
        List<Storage> storageList = storageMapper.getStorageAjax(stockOut.getGoodsId(), stockOut.getGoodsType(),
                stockOut.getGoodsQuality(), stockOut.getRepositoryId());
        if (storageList.size()>0){
            Storage storageOld = storageList.get(0);
            storageOld.setUpdateDate(new Date());
            int compare = Integer.compare(storageOld.getCurrentNum(), stockOut.getGoodsNumber());
            switch (compare){
                case 1:
                    storageOld.setCurrentNum(storageOld.getCurrentNum()-stockOut.getGoodsNumber());
                    break;
                case 0:
                    storageOld.setCurrentNum(0);
                    storageOld.setStorageStatus("1");
                    break;
                case -1:
                    throw new StorageException("库存数量不足");
            }
            storageMapper.updateByPrimaryKeySelective(storageOld);
        }else {
            throw new StorageException("没有该货物的库存");
        }


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
