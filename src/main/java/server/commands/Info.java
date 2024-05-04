package main.java.server.commands;

import main.java.common.network.Response;
import main.java.server.modules.CollectionManager;
import main.java.client.misc.ConsoleGod;
import main.java.common.model.Flat;
import main.java.server.modules.Server;

import java.io.Serializable;

public class Info extends Command{
    @Override
    public Response call(String strArg, Serializable objectArg) {
        CollectionManager manager = Server.getInstance().getCollectionManager();
        StringBuilder ret = new StringBuilder();
        ret.append("collection type: ").append(manager.getCollection().getClass().getSimpleName()).append("\n");
        ret.append("element type: ").append(Flat.class.getSimpleName());
        ret.append("element amount: ").append(manager.getCollection().size()).append("\n");
        return new Response(ret.toString());
    }

    @Override
    public String getDescription() {
        return "outputs collection info: type, initialization date, amount of elements";
    }
}
