/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Lev Tien
 */
public class GPSPoint extends Coordinate {

    private String userId = "";
    private String time;

    public GPSPoint() {
        super();
    }

    public GPSPoint(double lat, double lng, String time) {
        super(lat, lng);
        this.time = time;
    }

    public GPSPoint(String userId, String time, double lat, double lng) {
        super(lat, lng);
        this.userId = userId;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    public String toString() {
        return super.toString() + ", time=" + time;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.time);
        return hash;
    }

    /**
     * only compare longitude & latitude. for reducing
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GPSPoint other = (GPSPoint) obj;

        // get only 5 decimals (~0.8 meter)
        // +-0.8m still equals
        double lat1 = round(this.getLat(), 5);
        double lng1 = round(this.getLng(), 5);
        double lat2 = round(other.getLat(), 5);
        double lng2 = round(other.getLng(), 5);
        if (!(lat1 == lat2) || !(lng1 == lng2)) {
            return false;
        }
        return true;
    }

    /**
     * round(11.11111,2) return 11.11
     *
     * @param value the number
     * @param places number decimal
     * @return a double number
     */
    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String[] args) {
        GPSPoint p1 = new GPSPoint(39.806282, 116.4494, "");
        GPSPoint p2 = new GPSPoint(39.806299, 116.4494, "jn");

        List<GPSPoint> list = new ArrayList<>();
        list.add(p1);

        System.out.println(list.contains(p2));
    }

}
