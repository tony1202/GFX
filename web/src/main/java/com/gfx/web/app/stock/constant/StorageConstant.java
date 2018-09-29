package com.gfx.web.app.stock.constant;

/**
 * @author tony
 * @date 2018/9/29
 */
public abstract class StorageConstant {

    public interface SearchType{
        /**all*/
        String ALL = "searchAll";
        /**商品size*/
        String SIZE = "searchByGoodsSize";
        /**商品id*/
        String ID = "searchByGoodsID";
        /**商品品牌*/
        String BRAND = "searchByGoodsBrand";
    }
}
