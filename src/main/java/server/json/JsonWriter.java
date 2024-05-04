package main.java.server.json;

import main.java.server.modules.Converter;
import main.java.common.model.Flat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;

public class JsonWriter {

    public void write(Collection<Flat> rows){

        String filepath = "./data.json";
        StringBuilder data = new StringBuilder();

        data.append("[\n");
        for (Flat row : rows){
            String json = Converter.mapToJson(Converter.flatToMap(row));
            data.append(Converter.tabJson(json));
        }
        data.deleteCharAt(data.length() - 1);
        data.append("\n]");

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filepath))){
            writer.write(data.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
