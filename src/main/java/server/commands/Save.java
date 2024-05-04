package main.java.server.commands;

import main.java.common.network.Response;
import main.java.server.modules.StorageManager;

import java.io.Serializable;

public class Save extends Command{
    @Override
    public Response call(String strArg, Serializable objectArg) {
        StorageManager.saveToStorage();
        return new Response("");
    }

    @Override
    public String getDescription() {
        return "save collection to file";
    }

}
