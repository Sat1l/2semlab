package main.java.server.commands;

import main.java.common.misc.FlatData;
import main.java.common.network.Response;
import main.java.server.modules.CollectionManager;
import main.java.server.modules.Server;

import java.io.Serializable;

public class UpdateId extends Command{
    @Override
    public Response call(String strArg, Serializable objectArg) {
        long id = Long.parseLong(strArg);
        CollectionManager manager = Server.getInstance().getCollectionManager();
        FlatData flatData = (FlatData) objectArg;
        if(manager.updateById(id, flatData).equals("success")){
            return new Response("successfully updated a flat with id " + id);
        }
        return new Response("failed: could not find a flat with " + id + " to update");
    }

    @Override
    public String getDescription() {
        return "update element's properties by id";
    }
}
