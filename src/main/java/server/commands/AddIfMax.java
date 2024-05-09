package main.java.server.commands;

import main.java.common.misc.FlatData;
import main.java.common.model.Flat;
import main.java.common.network.Response;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class AddIfMax extends Command {
    @Override
    public Response call(String strArg, Serializable objArg) {
        Collection<Flat> set = Server.getInstance().getCollectionManager().getCollection();
        FlatData flatData = (FlatData) objArg;
        long valueAdd = flatData.getValue();
        Optional<Flat> maxItem = set.stream()
                .max(Comparator.comparingLong(Flat::getValue));

        if (maxItem.isPresent()) {
            if (valueAdd > maxItem.get().getValue()) {
                Server.getInstance().getCollectionManager().addFlat(flatData);
                return new Response("Item successfully added to collection.");
            } else {
                return new Response("Submitted element will not be the max element in collection.");
            }
        } else {
            return new Response("Collection is empty.");
        }
    }

    @Override
    public String getDescription() {
        return "adds element to collection if it's value exceeds the max one of the collection";
    }
}
