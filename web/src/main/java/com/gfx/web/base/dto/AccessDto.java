package com.gfx.web.base.dto;

import java.io.Serializable;

/**
 * @author tony
 * @date 2018/10/8
 */
public class AccessDto implements Serializable{

    private static final long serialVersionUID = 7819448438831463342L;
    private String userId;
    private String oldPassWord;
    private String newPassWord;
    private String rePassWord;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public String getRePassWord() {
        return rePassWord;
    }

    public void setRePassWord(String rePassWord) {
        this.rePassWord = rePassWord;
    }
}
