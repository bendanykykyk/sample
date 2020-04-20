package com.example.hello.exception;

import com.example.hello.enums.ResultEnum;

public class LuckymoneyException extends RuntimeException{

    private Integer code;

    public LuckymoneyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
