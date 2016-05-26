/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.model;

import java.util.ArrayList;

/**
 *
 * @author Lev Tien
 */
public class StayPoint {

    private int id;
    private String userId = "001";
    private ArrayList<GPSPoint> arr = new ArrayList<>();    //list of point 
    private Coordinate avgCoordinate = new Coordinate();    //centroid 
    private String startTime;
    private String endTime;

    public StayPoint() {

    }

    public StayPoint(Coordinate avgCoordinate, String startTime, String endTime) {
        this.avgCoordinate = avgCoordinate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coordinate getAvgCoordinate() {
        return avgCoordinate;
    }

    public void setAvgCoordinate(Coordinate avgCoordinate) {
        this.avgCoordinate = avgCoordinate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void computeAvgCoordinate() {
        if (arr.size() <= 0) {
            return;
        }
        double lat = 0, lng = 0;
        for (Coordinate p : arr) {
            lat += p.getLat();
            lng += p.getLng();
        }
        avgCoordinate.setLat(GPSPoint.round(lat / arr.size(), 5));
        avgCoordinate.setLng(GPSPoint.round(lng / arr.size(), 6));
        setStartTime(arr.get(0).getTime());
        setEndTime(arr.get(arr.size() - 1).getTime());
    }

    public ArrayList<GPSPoint> getArr() {
        return arr;
    }

    public void setArr(ArrayList<GPSPoint> arr) {
        this.arr = arr;
    }

    public void insertPoint(GPSPoint p) {
        arr.add(p);
    }

    public void showPoints() {
        System.out.println("sp size:" + arr.size());

        for (GPSPoint p : arr) {
            System.out.println("(" + p.getLat() + ", " + p.getLng() + ", " + p.getTime() + ")");
        }
    }

    public int size() {
        return arr.size();
    }

    @Override
    public String toString() {
        return "avgCoordinate=" + avgCoordinate + ", startTime=" + startTime + ", endTime=" + endTime;
    }

}
