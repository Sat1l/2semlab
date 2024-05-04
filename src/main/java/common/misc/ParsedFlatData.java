package main.java.common.misc;

public class ParsedFlatData {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String coordinatesX; //Поле не может быть null
    private String coordinatesY; //Поле не может быть null
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer area; //Значение поля должно быть больше 0
    private Long numberOfRooms; //Поле не может быть null, Значение поля должно быть больше 0
    private boolean balcony;
    private long timeToMetroOnFoot; //Значение поля должно быть больше 0
    private String view; //Поле может быть null
    private String houseName; //Поле не может быть null
    private Long houseYear; //Значение поля должно быть больше 0
    private long houseNumberOfLifts; //Значение поля должно быть больше 0

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCoordinatesX() {
        return coordinatesX;
    }

    public String getCoordinatesY() {
        return coordinatesY;
    }

    public String getCreationDate() {
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

    public String getView() {
        return view;
    }

    public String getHouseName() {
        return houseName;
    }

    public Long getHouseYear() {
        return houseYear;
    }

    public long getHouseNumberOfLifts() {
        return houseNumberOfLifts;
    }
}
