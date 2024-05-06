package main.java.server.commands;

import main.java.common.network.Response;

import java.io.Serializable;

public abstract class Command {

    public abstract Response call(String strArg, Serializable objArg);

    public abstract String getDescription();
}
