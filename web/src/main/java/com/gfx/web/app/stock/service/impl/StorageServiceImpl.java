package com.gfx.web.app.stock.service.impl;

import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.app.stock.constant.StorageConstant;
import com.gfx.web.app.stock.dto.StorageDto;
import com.gfx.web.app.stock.dto.StoragePagination;
import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.base.exception.StorageException;
import com.gfx.web.base.util.EJConvertor;
import com.gfx.web.base.util.UUIDUtils;
import com.gfx.web.common.dao.mapper.StorageMapper;
import com.gfx.web.common.entity.StockOperator;
import com.gfx.web.common.entity.Storage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mchange.lang.IntegerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Storage storageOld = checkExist(stockOperator.getGoodsId(), stockOperator.getGoodsQuality(), stockOperator.getGoodsType());
        //如果存在相应的存储,并且库位一致
        if (storageOld != null && StringUtils.equalsIgnoreCase(stockOperator.getRepositoryId(), storageOld.getRepositoryId())) {
            //修改当前库存数量
            storageOld.setCurrentNum(stockOperator.getGoodsNumber() + storageOld.getCurrentNum());
            //如果本次入库采购
            if (StringUtils.equals(stockOperator.getStockType(),CommonConstant.StockInType.PURCHASE)){
                //修改货物的初始库存数量
                storageOld.setInitNum(stockOperator.getGoodsNumber() + storageOld.getInitNum());
            }
            storageOld.setUpdateDate(new Date());
            storageMapper.updateByPrimaryKeySelective(storageOld);
        } else {
            Storage storage = new Storage();
            //复制属性
            BeanUtils.copyProperties(stockOperator, storage);
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
        return storageMapper.getStorageAjax(goodsId, goodsType, goodsQuality, null);
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
        if (storageList.size() > 0) {
            Storage storageOld = storageList.get(0);
            storageOld.setUpdateDate(new Date());
            int compare = Integer.compare(storageOld.getCurrentNum(), stockOut.getGoodsNumber());
            switch (compare) {
                case 1:
                    storageOld.setCurrentNum(storageOld.getCurrentNum() - stockOut.getGoodsNumber());
                    break;
                case 0:
                    storageOld.setCurrentNum(0);
                    storageOld.setStorageStatus("1");
                    break;
                case -1:
                    throw new StorageException("库存数量不足");
            }
            storageMapper.updateByPrimaryKeySelective(storageOld);
        } else {
            throw new StorageException("没有该货物的库存");
        }
    }

    /**
     * 分页查询库存
     *
     * @param pagination 分页条件
     * @return
     */
    @Override
    public Map<String, Object> getStorageList(StoragePagination pagination) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        params.put("goodsType", pagination.getGoodsType());
        switch (pagination.getSearchType()) {
            case StorageConstant.SearchType.ALL:
                break;
            case StorageConstant.SearchType.ID:
                params.put("goodsId", pagination.getKeyWord());
                break;
            case StorageConstant.SearchType.SIZE:
                params.put("goodsSize", pagination.getKeyWord());
                break;
            default:
                break;
        }
        Page<Object> page = null;
        if (pagination.getOffset() >= 0 && pagination.getLimit() >= 0) {

            page = PageHelper.startPage(pagination.getOffset(), pagination.getLimit(), true);
        }
        List<StorageDto> list = storageMapper.getStorageList(params);
        if (page!=null){
            result.put("total", page.getTotal());
        }
        result.put("data", list);
        return result;
    }

    /**
     * 导出Excel
     *
     * @param pagination      导出数据条件
     * @param storageDtoClass 数据源
     * @return 文件
     */
    @Override
    public File exportStorageRecord(StoragePagination pagination, Class<StorageDto> storageDtoClass) {
        Map<String, Object> storageMap = getStorageList(pagination);
        List<StorageDto> storageList = (List<StorageDto>) storageMap.get("data");
        EJConvertor ejConvertor = new EJConvertor();
        return ejConvertor.excelWriter(storageDtoClass, storageList);
    }

    /**
     * 跟新库存
     *
     * @param storage 库存信息
     * @return
     */
    @Override
    public boolean updateStorage(Storage storage) {
        return storageMapper.updateByPrimaryKeySelective(storage)==1;
    }


    /**
     * 删除库存
     *
     * @param storageId 库存id
     * @return
     */
    @Override
    public boolean deleteStorage(String storageId) {
        return storageMapper.deleteByPrimaryKey(storageId)==1;
    }

    /**
     * 查询获取库存情况
     *
     * @param goodsId      货物id
     * @param goodsQuality 货物成色
     * @param goodsType    货物类型
     * @return
     */
    private Storage checkExist(String goodsId, String goodsQuality, String goodsType) {
        List<Storage> storages = storageMapper.checkExist(goodsId, goodsQuality, goodsType);
        if (storages.size() > 0) {
            return storages.get(0);
        }
        return null;
    }
}
