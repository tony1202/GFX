package com.gfx.web.app.stock.service.impl;

import com.gfx.web.app.baseData.service.DirectoryService;
import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.app.stock.constant.StockRecordConstant;
import com.gfx.web.app.stock.dto.StockRecordDto;
import com.gfx.web.app.stock.dto.StockRecordPagination;
import com.gfx.web.app.stock.service.StockRecordService;
import com.gfx.web.app.stock.service.StorageService;
import com.gfx.web.base.operate.UserOperation;
import com.gfx.web.common.dao.mapper.StockOperatorMapper;
import com.gfx.web.common.entity.Dir;
import com.gfx.web.common.entity.StockOperator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/27
 */
@Service
public class StockRecordServiceImpl implements StockRecordService{

    private static final Logger log = LoggerFactory.getLogger(StockRecordServiceImpl.class);
    protected final DirectoryService directoryService;
    private StockOperatorMapper stockOperatorMapper;
    protected final StorageService storageService;
    @Autowired
    public StockRecordServiceImpl(StockOperatorMapper stockOperatorMapper, StorageService storageService, DirectoryService directoryService) {
        this.stockOperatorMapper = stockOperatorMapper;
        this.storageService = storageService;
        this.directoryService = directoryService;
    }

    /**
     * 入库操作
     *
     * @param stockIn 入库信息
     * @return 新增结果
     */
    @Override
    @Transactional
    @UserOperation("入库操作")
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

    /**
     * 出库操作
     *
     * @param stockOut 出库信息
     * @return
     */
    @Override
    @Transactional
    @UserOperation(value = "出库操作")
    public boolean stockOut(StockOperator stockOut) {
        //更新库存
        storageService.reduceStorage(stockOut);
        int insert = stockOperatorMapper.insertSelective(stockOut);
        return insert==1;
    }

    /**
     * 分页查询出入库记录
     *
     * @param pagination 分页数据
     * @return key:total - 数量; key:data - 具体数据
     */
    @Override
    public Map<String, Object> searchStockRecord(StockRecordPagination pagination) {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> params = new HashMap<>();
        params.put("goodsType",pagination.getGoodsType());
        List<Dir> dirs = null;
        switch (pagination.getSearchType()){
            case StockRecordConstant.SearchType.STOCK_IN:
                dirs = directoryService.getListDir(CommonConstant.DirCid.STOCKIN);
                break;
            case StockRecordConstant.SearchType.STOCK_OUT:
                dirs = directoryService.getListDir(CommonConstant.DirCid.STOCKOUT);
                break;
            default:break;
        }
        params.put("stockType",dirs);
        if (pagination.getStartDate()!=null){
            params.put("startDate",pagination.getStartDate());
        }
        if (pagination.getEndDate()!=null){
            params.put("endDate",pagination.getEndDate());
        }
        Page<Object> page = PageHelper.startPage(pagination.getOffset(), pagination.getLimit(), true);
        List<StockRecordDto> list = stockOperatorMapper.searchStockRecord(params);
        result.put("total",page.getTotal());
        result.put("data",list);
        return result;
    }
}
