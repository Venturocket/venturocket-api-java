package com.venturocket.api.client;

import com.sun.jersey.api.client.ClientResponse;

/**
 * User: Joe Linn
 * Date: 1/4/14
 * Time: 5:59 PM
 */
public class ApiException extends RuntimeException{
    protected ClientResponse.Status status;

    protected String message;

    public ApiException(ClientResponse.Status status, String message){
        super(message);
        this.status = status;
        this.message = message;
    }

    public ClientResponse.Status getStatus(){
        return status;
    }

    @Override
    public String toString(){
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return String.format("%s: %s %s", s, status.getStatusCode(), message);
    }
}
