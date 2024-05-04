package main.java.server.commands;

import main.java.common.network.Response;
import main.java.server.json.JsonParser;
import main.java.server.modules.Server;

import java.io.Serializable;

public class Parse extends Command {

    @Override
    public Response call(String arg, Serializable objectArg) {
        JsonParser parser = new JsonParser();
        Server.getInstance().getCollectionManager().setCollection(parser.parse());
        System.out.println("Коллекция успешно загружена из файла");
        return new Response("Коллекция успешно загружена из файла");
    }

    @Override
    public String getDescription() {
        return "debug program used to parse data from json to program";
    }
}

