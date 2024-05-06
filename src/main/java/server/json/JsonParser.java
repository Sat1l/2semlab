package main.java.server.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import main.java.server.modules.Converter;
import main.java.common.misc.ParsedFlatData;
import main.java.common.model.Flat;
import main.java.type.adapters.LocalDateTypeAdapter;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class JsonParser {

    public Collection<Flat> parse() {
        String filepath = "./data.json";
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

            for (ParsedFlatData parsedFlatData : collection){
                flats.add(Converter.parsedToRealFlat(parsedFlatData));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }

        return flats;
    }
}
