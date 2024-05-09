package main.java.server.commands;

import main.java.common.network.Response;
import main.java.server.modules.CollectionManager;
import main.java.common.model.Flat;
import main.java.server.modules.Server;

import java.io.Serializable;

public class Info extends Command{
    @Override
    public Response call(String strArg, Serializable objectArg) {
        CollectionManager manager = Server.getInstance().getCollectionManager();
        String ret = "collection type: " + manager.getCollection().getClass().getSimpleName() + "\n" +
                "element type: " + Flat.class.getSimpleName() + "\n" +
                "element amount: " + manager.getCollection().size() + "\n";
        return new Response(ret);
    }

    @Override
    public String getDescription() {
        return "outputs collection info: type, initialization date, amount of elements";
    }
}
