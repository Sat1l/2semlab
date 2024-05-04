package main.java.common.exceptions;

public class NoSuchCommandException extends RuntimeException {
    private final String command;
    public NoSuchCommandException(String command){
        this.command = command;
    }

    public String getMessage(){
        return "command " + command + " is non-existent";
    }
}
