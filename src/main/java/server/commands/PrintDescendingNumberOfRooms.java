package main.java.server.commands;

import main.java.common.model.Flat;
import main.java.common.network.Response;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrintDescendingNumberOfRooms extends Command {
    @Override
    public Response call(String strArg, Serializable objArg) {

        List<Flat> flats = new ArrayList<>(Server.getInstance().getCollectionManager().getCollection());
        flats.sort(Comparator.comparing(Flat::getNumberOfRooms).reversed());
        StringBuilder toret = new StringBuilder("sorted amounts of rooms: ");
        for (Flat flat : flats) {
            toret.append(flat.getNumberOfRooms()).append(", ");
        }
        return new Response(toret.toString());
    }

    @Override
    public String getDescription() {
        return "prints the number of rooms in descending order";
    }
}
