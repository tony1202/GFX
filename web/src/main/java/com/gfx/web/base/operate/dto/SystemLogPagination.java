package com.gfx.web.base.operate.dto;

import com.gfx.web.base.dto.Pagination;

/**
 * @author tony
 * @date 2018/10/8
 */
public class SystemLogPagination extends Pagination {
    private String userName;
    private String accessType;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
