package main.java.server.modules;

import main.java.common.misc.FlatData;
import main.java.common.misc.ParsedFlatData;
import main.java.common.model.Coordinates;
import main.java.common.model.Flat;
import main.java.common.model.House;
import main.java.common.model.View;

import java.util.LinkedHashMap;
import java.util.Map;
public class Converter {
    public static Map<String, Object> flatToMap(Flat flat){
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("id", flat.getId());
        data.put("name", flat.getName());
        data.put("coordinatesX", flat.getCoordinates().getX());
        data.put("coordinatesY", flat.getCoordinates().getY());
        data.put("creationDate", flat.getCreationDate());
        data.put("area", flat.getArea());
        data.put("numberOfRooms", flat.getNumberOfRooms());
        data.put("balcony", flat.isBalcony());
        data.put("timeToMetroOnFoot", flat.getTimeToMetroOnFoot());
        data.put("view", flat.getView());
        data.put("houseName", flat.getHouse().getName());
        data.put("houseYear", flat.getHouse().getYear());
        data.put("houseNumberOfLifts", flat.getHouse().getNumberOfLifts());
        data.put("value", flat.getValue());
        return data;
    }

    public static String mapToJson(Map<String, Object> map){
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        for (Map.Entry<String, Object> entry : map.entrySet()){
            json.append("\t\"").append(entry.getKey()).append("\":");
            if ((entry.getValue() instanceof Boolean) || (entry.getValue() instanceof Integer)){
                json.append(entry.getValue());
            } else {
                json.append("\"").append(entry.getValue()).append("\"");
            }
            json.append(",\n");
        }
        json.deleteCharAt(json.length() - 2);
        json.append("},\n");
        return json.toString();
    }

    public static String tabJson(String json){
        String[] rows = json.split("\n");
        StringBuilder tabbedJson = new StringBuilder();
        for (String row : rows){
            tabbedJson.append("\n\t").append(row);
        }
        return tabbedJson.toString();
    }

    public static Flat parsedToRealFlat(ParsedFlatData flat) {
        FlatData flatData = new FlatData();
        flatData.setName(flat.getName());
        Coordinates coordinates = new Coordinates();
        coordinates.setX(Double.parseDouble(flat.getCoordinatesX()));
        coordinates.setY(Integer.parseInt(flat.getCoordinatesY()));
        flatData.setCoordinates(coordinates);
        flatData.setArea(flat.getArea());
        flatData.setNumberOfRooms(flat.getNumberOfRooms());
        flatData.setBalcony(flat.isBalcony());
        flatData.setTimeToMetroOnFoot(flat.getTimeToMetroOnFoot());
        flatData.setView(View.valueOf(flat.getView()));
        House house = new House();
        house.setName(flat.getHouseName());
        house.setYear(flat.getHouseYear());
        house.setNumberOfLifts(flat.getHouseNumberOfLifts());
        flatData.setHouse(house);
        flatData.setValue();
        return new Flat(flat.getId(), flatData);
    }
}
