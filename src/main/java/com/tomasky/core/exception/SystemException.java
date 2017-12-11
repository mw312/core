package com.tomasky.core.exception;

import com.tomasky.core.enums.ResultCode;

public class SystemException extends RuntimeException {

    private String status;
    private String msg;

    public SystemException(ResultCode resultCode) {
        this.status = resultCode.getStatus();
        this.msg = resultCode.getMessage();
    }

    public SystemException(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
