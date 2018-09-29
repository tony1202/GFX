package com.gfx.web.base.exception;

/**
 * @author tony
 * @date 2018/9/26
 */
public class BaseException extends RuntimeException {

    private String exceptionDesc;

    public BaseException(Exception e){
        super(e);
    }

    public BaseException(Exception e,String exceptionDesc){
        super(e);
        this.exceptionDesc = exceptionDesc;
    }

    public BaseException(String exceptionDesc) {
        super(exceptionDesc);
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }
}
