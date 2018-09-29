package com.gfx.web.base.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tony
 * @date 2018/9/18
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = -131128495747201581L;
    /**分页大小*/
    private int limit;
    /**偏移量*/
    private int offset;
    /**查询类型*/
    private String searchType;
    /**查询关键字*/
    private String keyWord;
    /**开始时间*/
    private Date startDate;
    /**结束时间*/
    private Date endDate;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
