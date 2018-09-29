package com.gfx.web.app.stock.dto;

import com.gfx.web.base.dto.Pagination;

/**
 * @author tony
 * @date 2018/9/29
 */
public class StockRecordPagination extends Pagination {
    /**货物类型*/
    private String goodsType;

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
}
