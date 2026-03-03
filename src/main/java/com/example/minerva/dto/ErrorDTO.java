package com.example.minerva.dto;

public class ErrorDTO {
    private String message,
            requestUri;
    private int statusCode;
    private Throwable exception;


    public ErrorDTO(int statusCode,
                    String message,
                    Throwable exception,
                    String requestUri){
        this.statusCode = statusCode;
        this.message = message;
        this.exception = exception;
        this.requestUri = requestUri;
    }

    public Integer getStatusCode(){
        return this.statusCode;
    }
    public String getMessage(){
        return this.message;
    }
    public Throwable getException(){
        return this.exception;
    }
    public String getRequestUri(){
        return this.requestUri;
    }
}
