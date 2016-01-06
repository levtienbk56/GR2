/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lev Tien
 */
public class GPSPoint extends Coordinate {

    private String time;

    public GPSPoint() {
        super();
    }

    public GPSPoint(double lat, double lng, String time) {
        super(lat, lng);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static void main(String[] args) {
        System.out.println(GPSPoint.interval("2008-02-02 13:30:48", "2008-02-02 13:30:53"));
    }

    public static int intervalTest(String time, String time0) {
        return Integer.valueOf(time) - Integer.valueOf(time0);
    }

    /**
     * input: String format "yyyy-MM-dd HH:mm:ss"
     *
     * @param time1
     * @param time2
     * @return interval time in second
     */
    public static long interval(String time1, String time2) {
        try {
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = fm.parse(time1);
            Date date2 = fm.parse(time2);
            return (date2.getTime() - date1.getTime()) / 1000;
        } catch (ParseException ex) {

        }

        return 0;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("time: " + time);
    }

}
