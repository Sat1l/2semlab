package main.java.common.model;


import main.java.common.misc.FlatData;

import java.time.LocalDate;

public class Flat {
    private final long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Integer area; //Значение поля должно быть больше 0
    private final Long numberOfRooms; //Поле не может быть null, Значение поля должно быть больше 0
    private final boolean balcony;
    private final long timeToMetroOnFoot; //Значение поля должно быть больше 0
    private final View view; //Поле может быть null
    private final House house; //Поле не может быть null

    public Flat(long id, FlatData flatData){
        this.id = id;
        this.name = flatData.getName();
        this.coordinates = flatData.getCoordinates();
        this.creationDate = LocalDate.now();
        this.area = flatData.getArea();
        this.numberOfRooms = flatData.getNumberOfRooms();
        this.balcony = flatData.isBalcony();
        this.timeToMetroOnFoot = flatData.getTimeToMetroOnFoot();
        this.view = flatData.getView();
        this.house = flatData.getHouse();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Integer getArea() {
        return area;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public long getTimeToMetroOnFoot() {
        return timeToMetroOnFoot;
    }

    public View getView() {
        return view;
    }

    public House getHouse() {
        return house;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinateX=" + coordinates.getX() +
                ", coordinateY=" + coordinates.getY() +
                ", creationDate=" + creationDate +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", balcony=" + balcony +
                ", timeToMetroOnFoot=" + timeToMetroOnFoot +
                ", view=" + view +
                ", houseName=" + house.getName() +
                ", houseYear=" + house.getYear() +
                ", houseNumberOfLifts=" + house.getNumberOfLifts() +
                '}';
    }
}