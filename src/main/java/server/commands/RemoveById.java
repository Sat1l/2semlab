package main.java.server.commands;

import main.java.common.model.Flat;
import main.java.common.network.Response;
import main.java.server.modules.CollectionManager;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.Collection;

public class RemoveById extends Command{
    @Override
    public Response call(String strArg, Serializable objectArg) {
        long id = Long.parseLong(strArg);
        CollectionManager manager = Server.getInstance().getCollectionManager();
        Collection<Flat> flats =  manager.getCollection();
        Flat flat = manager.getFlatById(id);
        if (flat != null){
            flats.remove(manager.getFlatById(id));
            return new Response("removed flat with id" + id);
        } else {
            return new Response("server could not find this id in collection thus its not getting removed");
        }
    }

    @Override
    public String getDescription() {
        return "remove element from collection by id";
    }
}
