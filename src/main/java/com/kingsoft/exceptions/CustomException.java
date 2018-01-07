package com.kingsoft.exceptions;
/**
 * Created by LUGUANGRUI on 2016/11/29.
 */
public class CustomException extends Exception {


    private int httpCode;
    private int code ;
    private String message ;

    public CustomException(){
        super();
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(int httpCode, int code,String message){
        super();
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

