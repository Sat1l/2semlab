package main.java.server.commands;

import main.java.common.model.Flat;
import main.java.common.network.Response;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.HashSet;

public class Clear extends Command{
    @Override
    public Response call(String strArg, Serializable objArg) {
        Server.getInstance().getCollectionManager().setCollection(new HashSet<Flat>());
        return new Response("collection had been successfully cleared");
    }

    @Override
    public String getDescription() {
        return "clear collection";
    }
}
