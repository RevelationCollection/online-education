package com.study.edu.service.common.exception;

import com.study.edu.common.ResultCodeEnum;
import lombok.Data;

@Data
public class GuliException extends RuntimeException {
    //状态码
    private Integer code;

    public GuliException(Integer code ,String message){
        super(message);
        this.code = code;
    }

    public GuliException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString(){
        return "GuliException{" +"code=" + code +", message=" + this.getMessage() +'}';
    }
}
