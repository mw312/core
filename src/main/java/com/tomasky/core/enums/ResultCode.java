package com.tomasky.core.enums;

import com.tomasky.core.Constants;

/**
 * 返回状态码
 * 定义公用的返回状态码，具体应用业务如需自行定义请继承该枚举
 */
public enum ResultCode {

    SUCCESS("" + Constants.HTTP_200, "success"),
    PARAM_ERROR("" + Constants.HTTP_400, "缺少必要参数/参数无效！"),
    UNAUTHORIZED("" + Constants.HTTP_401, "未授权/签名有误！"),
    TIME_OUT("" + Constants.HTTP_402, "请求超时！"),
    FORBIDDEN("" + Constants.HTTP_403, "禁止访问！"),
    NOT_FOUND("" + Constants.HTTP_404, "资源不存在！"),

    COMMEN_SYSTEM_EXCEPTION("" + Constants.HTTP_500, "系统内部异常！"),
    COMMEN_BUSINESS_EXCEPTION("" + Constants.HTTP_501, "业务异常！"),
    COMMON_RPC_EXCEPTION("" + Constants.HTTP_502, "系统RPC异常！"),
    OTHER_EXCEPTION("" + Constants.HTTP_503, "其他错误！"),
    NO_CONTENT("" + Constants.HTTP_204, "No Content");//No Content没有新文档。浏览器应该继续显示原来的文档。

    /**
     * 状态码
     */
    private String status;

    /**
     * 状态码描述
     */
    private String message;

    ResultCode(String status, String message) {
        this.message = message;
        this.status = status;
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
}
