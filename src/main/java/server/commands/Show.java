package main.java.server.commands;

import main.java.common.network.Response;
import main.java.server.modules.CollectionManager;
import main.java.common.model.Flat;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.Collection;

public class Show extends Command{

    @Override
    public Response call(String strArg, Serializable objectArg) {
        CollectionManager manager = Server.getInstance().getCollectionManager();
        Collection<Flat> flats = manager.getCollection();
        StringBuilder ret = new StringBuilder();

        for(Flat flat : flats){
            ret.append(flat.toString()).append("\n");
        }
        return new Response(ret.toString());
    }

    @Override
    public String getDescription() {
        return "show collection";
    }
}
