package com.gfx.web.app.stock.dto;

import com.gfx.web.common.entity.Storage;

/**
 * @author tony
 * @date 2018/9/29
 */
public class StorageDto extends Storage {
    /**货物成色名*/
    private String goodsQualityName;
    /**商品类型名称*/
    private String goodsTypeName;

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsQualityName() {
        return goodsQualityName;
    }

    public void setGoodsQualityName(String goodsQualityName) {
        this.goodsQualityName = goodsQualityName;
    }
}
