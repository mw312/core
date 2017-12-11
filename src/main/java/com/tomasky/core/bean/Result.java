package com.tomasky.core.bean;

import com.tomasky.core.enums.ResultCode;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * AJAX 统一返回对象
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 结果状态码
     */
    private String status;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 业务数据
     */
    private Object data;

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCode resultCode, Object data) {
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public Result(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 附加返回错误信息
     *
     * @param resultCode
     * @param extraRrrorMsg
     */
    public Result(ResultCode resultCode, String extraRrrorMsg) {
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage() + (null != extraRrrorMsg ? extraRrrorMsg : "");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
