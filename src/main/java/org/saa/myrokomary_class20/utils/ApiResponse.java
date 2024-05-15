package org.saa.myrokomary_class20.utils;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
    T header; //must or will not work
    HttpStatus status; //must or will not work
    Integer statusCode;
    Date timestamp; //must or will not work

    RepresentationModel<?> links;
    T body; //must or will not work

    T data; //optional
    String details; //must or will not work
    String message; //must or will not work
    T error; //must or will not work
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }

    public RepresentationModel<?> getLinks() {
        return links;
    }

    public void setLinks(RepresentationModel<?> links) {
        this.links = links;
    }

    public static ApiResponse build(HttpStatus status){
        return new ApiResponse(status);
    }
    private ApiResponse(HttpStatus status){
        this.status = status;
        this.statusCode = status.value();
        this.timestamp = new Date();
    }

    public ApiResponse header(T body){
        this.header = body;
        return this;
    }

    public ApiResponse links(WebMvcLinkBuilder linkBuilder, String rel)  {
        this.links = RepresentationModel.of(body);
        this.links.add(linkBuilder.withRel(rel));
        return this;
    }

    public ApiResponse body(T body, WebMvcLinkBuilder linkBuilder, String rel)  {
        this.links(linkBuilder,rel);
        this.body = body;
        return this;
    }

    public ApiResponse body(T body){
        this.body = body;
        return this;
    }

    public ApiResponse data(T data){
        this.data = data;
        return this;
    }

    public ApiResponse details(String details){
        this.details = details;
        return this;
    }

    public ApiResponse message(String message){
        this.message = message;
        return this;
    }

    public ApiResponse error(T error){
        this.error = error;
        return this;
    }


}
