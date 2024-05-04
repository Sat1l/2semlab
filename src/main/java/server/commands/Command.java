package main.java.server.commands;

import main.java.common.network.Response;

import java.io.Serializable;
import java.util.ResourceBundle;

public abstract class Command {

    public String description;

    public abstract Response call(String strArg, Serializable objArg);



    public abstract String getDescription();
}
