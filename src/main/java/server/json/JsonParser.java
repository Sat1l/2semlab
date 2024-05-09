package main.java.server.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import main.java.client.modules.Client;
import main.java.server.modules.Converter;
import main.java.common.misc.ParsedFlatData;
import main.java.common.model.Flat;
import main.java.server.modules.Server;
import main.java.type.adapters.LocalDateTypeAdapter;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class JsonParser {

    public Collection<Flat> parse() {
        String filepath = Server.getInstance().getColPath();
        Collection<ParsedFlatData> collection;
        Collection<Flat> flats = new HashSet<>();
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filepath));

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .create();
            collection = gson.fromJson(
                    new InputStreamReader(bis),
                    new TypeToken<Collection<ParsedFlatData>>() {
                    }.getType()
            );
            if (collection != null) {
                for (ParsedFlatData parsedFlatData : collection) {
                    flats.add(Converter.parsedToRealFlat(parsedFlatData));
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }

        return flats;
    }
}
