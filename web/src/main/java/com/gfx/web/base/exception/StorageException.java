package com.gfx.web.base.exception;

/**
 * 库存异常类
 * @author tony
 * @date 2018/9/29
 */
public class StorageException extends BaseException {
    public StorageException(Exception e) {
        super(e);
    }

    public StorageException(String exceptionDesc) {
        super(exceptionDesc);
    }

    public StorageException(Exception e, String exceptionDesc) {
        super(e, exceptionDesc);
    }
}
