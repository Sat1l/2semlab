package main.java.common.model;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Double x; //Максимальное значение поля: 815, Поле не может быть null
    private int y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
