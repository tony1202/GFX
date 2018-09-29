package com.gfx.web.app.stock.constant;

/**
 * @author tony
 * @date 2018/9/29
 */
public abstract class StockRecordConstant {

    /**
     * 搜索常量
     */
    public interface SearchType{
        /**所有*/
        String SEARCH_ALL = "all";
        /**入库*/
        String STOCK_IN = "stockInOnly";
        /**出库*/
        String STOCK_OUT = "stockOutOnly";
    }

}
