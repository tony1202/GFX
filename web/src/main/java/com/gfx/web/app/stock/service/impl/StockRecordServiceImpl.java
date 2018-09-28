package com.gfx.web.app.stock.service.impl;

import com.gfx.web.app.stock.service.StockRecordService;
import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.common.dao.mapper.StockOperatorMapper;
import com.gfx.web.common.entity.StockOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tony
 * @date 2018/9/27
 */
@Service
public class StockRecordServiceImpl implements StockRecordService{

    private static final Logger log = LoggerFactory.getLogger(StockRecordServiceImpl.class);

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
        try {
            //更新库存记录
            storageService.addStorage(stockIn);
            //记录入库记录
            int insert = stockOperatorMapper.insert(stockIn);
            return insert==1;
        } catch (Exception e) {
            log.warn("stockIn operate error -->",e);
            return false;
        }
    }
}
