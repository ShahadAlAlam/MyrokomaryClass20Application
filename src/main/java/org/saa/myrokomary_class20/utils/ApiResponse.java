package org.saa.myrokomary_class20.utils;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiResponse<T> {

    /*
     *** with out getter no data will show
     *** this is must
     * These variables must be declated to work as api response
     * HttpStatus status must set with constructor
     * Date timestamp must set with base constructor
     * String details must
     * T body must set with constructor
     * T header must set with constructor
     * String path no getter setter
     * String error
     * String message must set with constructor
     */
    String details; //must or will not work
    Date timestamp; //must or will not work
    Integer StatusCode;
    HttpStatus status; //must or will not work
    T body; //must or will not work
    T header; //must or will not work
    String message; //must or will not work

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }




    String error; //must or will not work
    String path; //must or will not work

    ApiResponse(){

    }

    public Integer getStatusCode() {
        return this.status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public T getBody() {
        return body;
    }

    public T getHeader() {
        return header;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public static ApiResponse build(HttpStatus status){
        return new ApiResponse(status);
    }
    private ApiResponse(HttpStatus status){
        this.status = status;
        this.timestamp = new Date();
    }

    public ApiResponse body(T body){
        this.body = body;
        return this;
    }

    public ApiResponse header(T body){
        this.header = body;
        return this;
    }

    public ApiResponse message(String message){
        this.message = message;
        return this;
    }

}
