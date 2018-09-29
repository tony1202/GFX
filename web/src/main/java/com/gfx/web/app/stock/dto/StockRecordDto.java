package com.gfx.web.app.stock.dto;

import com.gfx.web.common.entity.StockOperator;

/**
 * @author tony
 * @date 2018/9/29
 */
public class StockRecordDto extends StockOperator {
    /**操作人名*/
    private String operatorName;
    /**商品类型名称*/
    private String goodsTypeName;
    /**供应商或客户名*/
    private String supplierOrCustomerName;
    /**业务员人名*/
    private String saleManName;
    /**货物成色名*/
    private String goodsQualityName;
    /**具体操作名*/
    private String operateTypeName;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public String getSupplierOrCustomerName() {
        return supplierOrCustomerName;
    }

    public void setSupplierOrCustomerName(String supplierOrCustomerName) {
        this.supplierOrCustomerName = supplierOrCustomerName;
    }

    public String getSaleManName() {
        return saleManName;
    }

    public void setSaleManName(String saleManName) {
        this.saleManName = saleManName;
    }

    public String getGoodsQualityName() {
        return goodsQualityName;
    }

    public void setGoodsQualityName(String goodsQualityName) {
        this.goodsQualityName = goodsQualityName;
    }

    public String getOperateTypeName() {
        return operateTypeName;
    }

    public void setOperateTypeName(String operateTypeName) {
        this.operateTypeName = operateTypeName;
    }
}
