package main.java.server.commands;

import main.java.common.model.Flat;
import main.java.common.network.Response;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class RemoveLower extends Command{

    @Override
    public Response call(String strArg, Serializable objArg) {
        long id = Long.parseLong(strArg);
        Flat flatAnchor = Server.getInstance().getCollectionManager().getFlatById(id);
        Collection<Flat> collection = Server.getInstance().getCollectionManager().getCollection();
        long threshold = flatAnchor.getValue();

        List<Flat> filteredFlats = collection.stream()
                .filter(flat -> flat.getValue() >= threshold)
                .toList();

        Server.getInstance().getCollectionManager().setCollection(new HashSet<Flat>(filteredFlats));
        return new Response("successfully removed all flats with value lower than " + threshold);
    }

    @Override
    public String getDescription() {
        return null;
    }
}
