/*
 * Copyright (C) 2015 Lev Tien
 * 1002 B1 building - HUST , Ta Quang Buu, HN
 */
package model;

/**
 *
 * @author Lev Tien
 */
public class TdrivePOJO {

    public int id;
    public int userId;
    public Coordinate coordinate;
    public String time;
    public String formattedAddress;

    public TdrivePOJO() {
        coordinate = new Coordinate();
    }

    public void print() {
        System.out.println(id + ", " + userId + ", " + "(" + coordinate.getLat() + "," + coordinate.getLng() + ")" + time);
    }

}
