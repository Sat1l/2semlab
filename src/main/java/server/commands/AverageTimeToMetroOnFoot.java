package main.java.server.commands;

import main.java.common.network.Response;
import main.java.server.modules.Server;
import main.java.common.model.Flat;

import java.io.Serializable;

public class AverageTimeToMetroOnFoot extends Command {

    @Override
    public Response call(String strArg, Serializable objArg) {

        double averageValue = Server.getInstance().getCollectionManager().getCollection().stream()
                .mapToLong(Flat::getTimeToMetroOnFoot) // Map each Flat to its 'value' field
                .average()                 // Calculate the average
                .orElse(0);                // Default value if the collection is empty
        return new Response("average time to metro on foot: " + averageValue);
    }

    @Override
    public String getDescription() {
        return "prints the average time to metro on foot";
    }
}
