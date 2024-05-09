package main.java.common.network;

import java.io.Serializable;

public class Request implements Serializable {
    private final String commandName;
    private final String commandStrArg;
    private final Serializable commandObjArg;

    public Request(String commandName, String commandStrArg, Serializable commandObjArg) {
        this.commandName = commandName;
        this.commandStrArg = commandStrArg;
        this.commandObjArg = commandObjArg;
    }

    public Request(String commandName, String commandStrArg) {
        this(commandName, commandStrArg, null);
    }


    public String getCommandName() {
        return commandName;
    }

    public String getCommandStrArg() {
        return commandStrArg;
    }

    public Serializable getCommandObjArg() {
        return commandObjArg;
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", commandStrArg='" + commandStrArg + '\'' +
                ", commandObjArg=" + commandObjArg +
                '}';
    }
}