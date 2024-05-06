package main.java.common.network;

import java.io.Serializable;

public class Response implements Serializable {
    private String message;
    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}