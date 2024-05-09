package main.java.server.commands;

import main.java.common.model.Flat;
import main.java.common.network.Response;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class RemoveGreater extends Command {

    @Override
    public Response call(String strArg, Serializable objArg) {
        long id = Long.parseLong(strArg);
        Flat flatAnchor = Server.getInstance().getCollectionManager().getFlatById(id);
        Collection<Flat> collection = Server.getInstance().getCollectionManager().getCollection();
        long threshold = flatAnchor.getValue();

        List<Flat> filteredFlats = collection.stream()
                .filter(flat -> flat.getValue() <= threshold)
                .toList();

        Server.getInstance().getCollectionManager().setCollection(new HashSet<>(filteredFlats));
        return new Response("successfully removed all flats with value greater than " + threshold);
    }

    @Override
    public String getDescription() {
        return "removes all flats with value GREATER than the value of the flat with the specified id";
    }
}
