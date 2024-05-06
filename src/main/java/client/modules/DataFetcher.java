package main.java.client.modules;

import main.java.common.exceptions.CantBeNullException;
import main.java.common.misc.FlatData;
import main.java.common.model.Coordinates;
import main.java.common.model.House;
import main.java.common.model.View;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class DataFetcher {

    Scanner scanner;
    public FlatData fetch(){
        FlatData flatData = new FlatData();
        try {
            fetchName(flatData);
            fetchCoordinates(flatData);
            fetchArea(flatData);
            fetchNumberOfRooms(flatData);
            fetchBalcony(flatData);
            fetchTimeToMetroOnFoot(flatData);
            fetchView(flatData);
            fetchHouse(flatData);
        } catch (NoSuchElementException e){
            System.out.println("got an error input, stopping");
            return null;
        }
        return flatData;
    }

    public void fetchName(FlatData flatData){
        System.out.println("write a string for flat's name");
        String name = fetchString();
        flatData.setName(name);
    }


    public void fetchCoordinates(FlatData flatData){
        Coordinates coordinates = new Coordinates();
        System.out.println("write a double for X coordinate");
        Double coordX = fetchDouble();
        System.out.println("write an int for Y coordinate");
        int coordy = fetchInt();
        coordinates.setX(coordX);
        coordinates.setY(coordy);
        flatData.setCoordinates(coordinates);
    }

    public void fetchArea(FlatData flatData){
        System.out.println("write an int for flat's area");
        Integer area = fetchInt();
        flatData.setArea(area);
    }

    public void fetchNumberOfRooms(FlatData flatData){
        System.out.println("write an int for amount of rooms");
        Long numberOfRooms = fetchLong();
        flatData.setNumberOfRooms(numberOfRooms);
    }

    public void fetchBalcony(FlatData flatData){
        System.out.println("Does have balcony? Type (y/[n])");
        boolean data = fetchBoolean();
        flatData.setBalcony(data);
    }

    public void fetchTimeToMetroOnFoot(FlatData flatData){
        System.out.println("write a long");
        long timeToMetroOnFoot = fetchLong();
        flatData.setTimeToMetroOnFoot(timeToMetroOnFoot);
    }

    public void fetchView(FlatData flatData){
        System.out.println("write any member of enum VIEW, which are: STREET, PARK, BAD");
        View view = fetchEnumView();
        flatData.setView(view);
    }

    public void fetchHouse(FlatData flatData){
        House house = new House();
        System.out.println("write a String for housename");
        house.setName(fetchString());
        System.out.println("write a Long for year");
        house.setYear(fetchLong());
        System.out.println("write a long for numberOfLifts");
        house.setNumberOfLifts(fetchLong());
        flatData.setHouse(house);
    }

    public String fetchValue(boolean nullable) throws NoSuchElementException{
        scanner = Client.getInstance().getScanner();
        try {
            String value = scanner.next();
            if (nullable && (value == "")) {
                return null;
            } else if (!nullable && (value == "")) {
                throw new CantBeNullException();
            } else {
                return value;
            }
        } catch (NoSuchElementException e){
            throw new NoSuchElementException();
        }
    }

    public String fetchValue() throws NoSuchElementException{
        return fetchValue(false);
    }

    public int fetchInt() throws NoSuchElementException{
        while (true){
            try {
                return Integer.parseInt(fetchValue());
            } catch (NumberFormatException e){
                System.out.println("that's not an int u fool!");
            } catch (NoSuchElementException e){
                throw new NoSuchElementException();
            }
        }
    }

    public double fetchDouble() throws NoSuchElementException{
        while (true) {
            try {
                return Double.parseDouble(fetchValue());
            } catch (NumberFormatException e) {
                Client.getInstance().getScriptsStack().removeLast();
                System.out.println("that's not a double u fool!");
            } catch (NoSuchElementException e){
                throw new NoSuchElementException();
            }
        }
    }

    public long fetchLong() throws NoSuchElementException{
        while (true) {
            try {
                return Long.parseLong(fetchValue());
            } catch (NumberFormatException e) {
                System.out.println("that's not a long u fool!");
            }  catch (NoSuchElementException e){
                throw new NoSuchElementException();
            }
        }
    }


    public boolean fetchBoolean() throws NoSuchElementException{
        while (true) {
            try {
                String data = fetchValue(true);
                if (data.equals("y")){
                    return true;
                } else if (data.equals("n") || data.isEmpty()) {
                    return false;
                } else {
                    System.out.println("that's not allowed");
                }
            } catch (NumberFormatException e) {
                System.out.println("that's not an allowed string u fool! type either y/[n]");
            }  catch (NoSuchElementException e){
                throw new NoSuchElementException();
            }
        }
    }

    public View fetchEnumView() throws NoSuchElementException{
        while (true) {
            try {
                return View.valueOf(fetchValue().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("that's not an available ENUM name u fool!");
            } catch (NoSuchElementException e){
                throw new NoSuchElementException();
            }
        }
    }

    public String fetchString() throws NoSuchElementException{
        while (true){
            try{
                return fetchValue();
            } catch (CantBeNullException e){
                System.out.println("this field can't be null");
            }  catch (NoSuchElementException e){
                throw new NoSuchElementException();
            }
        }
    }

}
