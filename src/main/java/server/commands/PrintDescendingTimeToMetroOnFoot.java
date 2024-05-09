package main.java.server.commands;

import main.java.common.model.Flat;
import main.java.common.network.Response;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrintDescendingTimeToMetroOnFoot extends Command{

    @Override
    public Response call(String strArg, Serializable objArg) {

        List<Flat> flats = new ArrayList<>(Server.getInstance().getCollectionManager().getCollection());
        flats.sort(Comparator.comparing(Flat::getTimeToMetroOnFoot).reversed());
        StringBuilder toret = new StringBuilder("sorted time to metro on foot: ");
        for (Flat flat : flats) {
            toret.append(flat.getTimeToMetroOnFoot()).append(", ");
        }
        return new Response(toret.toString());
    }

    @Override
    public String getDescription() {
        return "prints the time to metro on foot in descending order";
    }
}
