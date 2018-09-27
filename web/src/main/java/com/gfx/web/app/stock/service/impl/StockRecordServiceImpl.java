package com.gfx.web.app.stock.service.impl;

import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.app.stock.service.StockRecordService;
import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.base.util.UUIDUtils;
import com.gfx.web.common.dao.mapper.StockOperatorMapper;
import com.gfx.web.common.entity.StockOperator;
import com.gfx.web.common.entity.Storage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author tony
 * @date 2018/9/27
 */
@Service
public class StockRecordServiceImpl implements StockRecordService{

    private StockOperatorMapper stockOperatorMapper;
    protected final StorageService storageService;
    @Autowired
    public StockRecordServiceImpl(StockOperatorMapper stockOperatorMapper, StorageService storageService) {
        this.stockOperatorMapper = stockOperatorMapper;
        this.storageService = storageService;
    }

    /**
     * 入库操作
     *
     * @param stockIn 入库信息
     * @return 新增结果
     */
    @Override
    @Transactional
    public boolean stockIn(StockOperator stockIn) {
        if (StringUtils.equals(CommonConstant.StockInType.PURCHASE,stockIn.getStockType())) {
            Storage storage = new Storage();
            BeanUtils.copyProperties(stockIn, storage);

            storage.setCreateDate(new Date());
            storage.setUpdateDate(new Date());
            storage.setCurrentNum(stockIn.getGoodsNumber());
            storage.setInitNum(stockIn.getGoodsNumber());
            //storageService.addStorage(storage);
        }
        return stockOperatorMapper.insertSelective(stockIn)==1;
    }
}
