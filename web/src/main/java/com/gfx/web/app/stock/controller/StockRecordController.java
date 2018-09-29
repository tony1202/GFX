package com.gfx.web.app.stock.controller;

import com.gfx.web.app.stock.service.StockRecordService;
import com.gfx.web.base.context.UserContextHolder;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.base.util.UUIDUtils;
import com.gfx.web.common.entity.StockOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/27
 */
@RestController
@RequestMapping("/stockRecord")
public class StockRecordController {

    private static final Logger log = LoggerFactory.getLogger(StockRecordController.class);

    private StockRecordService stockRecordService;

    @Autowired
    public StockRecordController(StockRecordService stockRecordService) {
        this.stockRecordService = stockRecordService;
    }

    /**
     * 入库操作
     *
     * @return 响应
     */
    @PostMapping("/stockIn")
    public Map<String, Object> stockIn(@RequestBody StockOperator stockIn) {
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        //生成id
        stockIn.setRecordId(UUIDUtils.uuid("I"));
        //设置操作人
        stockIn.setOperator(UserContextHolder.getUserInfo().getUserId());
        //设置操作时间
        stockIn.setOperatorDate(new Date());
        if (stockRecordService.stockIn(stockIn)) {
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
        }

        return vmsResponse.generateResponseBody();
    }

    /**
     * 出库操作
     * @param stockOut 出库信息
     * @return
     */
    @PostMapping("/stockOut")
    public Map<String,Object> stockOut(@RequestBody StockOperator stockOut){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        //生成ID
        stockOut.setRecordId(UUIDUtils.uuid("O"));
        //设置操作人
        stockOut.setOperator(UserContextHolder.getUserInfo().getUserId());
        //设置操作时间
        stockOut.setOperatorDate(new Date());
        if (stockRecordService.stockOut(stockOut)){
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
        }

        return vmsResponse.generateResponseBody();
    }

    /**
     * 出入库记录查询
     * @return 出入库记录
     */
    @GetMapping("/searchStockRecord")
    public Map<String,Object> searchStockRecord(Pagination pagination){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);

        return vmsResponse.generateResponseBody();
    }

}
