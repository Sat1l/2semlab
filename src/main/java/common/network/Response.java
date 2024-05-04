package main.java.common.network;

import java.io.Serializable;
import java.util.Stack;

public class Response implements Serializable {
    private String message;
    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}