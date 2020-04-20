package com.example.hello.enums;

public enum  ResultEnum {

    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    PAY_LESS_THAN_50(100,"你给的太少啦，50都没"),
    PAY_EQUAL_50(101,"你怎么刚好给50的，不要啊"),
    ;

    private String msg;

    private Integer code;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
