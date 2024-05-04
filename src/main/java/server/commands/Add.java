package main.java.server.commands;


import main.java.client.modules.DataFetcher;
import main.java.common.network.Response;
import main.java.server.modules.CollectionManager;
import main.java.server.modules.Server;
import main.java.common.misc.FlatData;

import java.io.Serializable;

public class Add extends Command {
    @Override
    public Response call(String strArg, Serializable objArg) {
        CollectionManager collectionManager = Server.getInstance().getCollectionManager();
        System.out.println(objArg.toString());
        FlatData flatData = (FlatData) objArg;
        collectionManager.addFlat(flatData);
        return new Response("item successfully added to collection!");
    }

    @Override
    public String getDescription() {
        return "add new element to collection using user input";
    }
}