package com.gfx.web.app.constant;

/**
 * 通用常量
 * @author tony
 * @date 2018/9/19
 */
public abstract class CommonConstant {
    /**
     * 货物常量
     */
    public interface GoodsConstant{
        /**查询所有*/
        String SEARCH_TYPE_ALL = "searchAll";
        /**根据id*/
        String SEARCH_TYPE_ID = "searchByID";
        /**根据name*/
        String SEARCH_TYPE_SIZE = "searchBySize";

        /**玻璃*/
        String GOOD_TYPE_GLASS = "0001";
        /**背光*/
        String GOOD_TYPE_BACKLIGHT = "0002";
        /**模组*/
        String GOOD_TYPE_MODULE = "0003";
        /**胶纸*/
        String GOOD_TYPE_TAPE = "0004";

    }

    /**
     * 仓库常量
     */
    public interface RepositoryConstant{
        /**查询所有*/
        String SEARCH_TYPE_ALL = "searchAll";
        /**根据id*/
        String SEARCH_TYPE_ID = "searchByID";
        /**根据name*/
        String SEARCH_TYPE_Address = "searchByAddress";
    }

    /**
     * 角色常量
     */
    public interface RoleConstant{
        /**系统管理员*/
        String ADMIN = "admin";
    }

    /**
     * 客户常量
     */
    public interface CustomerConstant{
        /**查询所有*/
        String SEARCH_TYPE_ALL = "searchAll";
        /**根据id*/
        String SEARCH_TYPE_LINK_MAN = "searchByLinkMan";
        /**根据客户名字*/
        String SEARCH_TYPE_NAME = "searchByName";
    }

    /**
     * 入库类型
     */
    public interface StockInType{
        /**采购入库*/
        String PURCHASE = "0201";
    }

    /**
     * 字典表分类ID
     */
    public interface DirCid{
        /**入库操作*/
        String STOCKIN = "02";
        /**出库操作*/
        String STOCKOUT = "03";
    }
}
