package main.java.server.modules;

import main.java.server.json.JsonParser;
import main.java.server.json.JsonWriter;
import main.java.common.model.Flat;

import java.util.Collection;

public class StorageManager {

    public static Collection<Flat> readStorage(){
        JsonParser parser = new JsonParser();
        return parser.parse();
    }

    public static void saveToStorage(){
        JsonWriter writer = new JsonWriter();
        Collection<Flat> flats = Server.getInstance().getCollectionManager().getCollection();
        writer.write(flats);
    }
}
