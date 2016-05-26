/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.model.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.hedspi.tienlv.gr.database.DBConnectHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hedspi.tienlv.gr.model.GPSPoint;
import org.hedspi.tienlv.gr.utils.GPSPointExtractor;

/**
 *  map với bảng gsppoints_geolife trong DB
 * @author Lev Tien
 */
public class GPSPointGeolifeDAO {

    private Connection connection;

    public GPSPointGeolifeDAO() {
        connection = DBConnectHelper.getDBConnection();
    }

    public static void main(String[] args) throws Exception {
        GPSPointGeolifeDAO dao = new GPSPointGeolifeDAO();
        dao.selectDateTest();
    }

    public void selectDateTest() {
        List<String> list = selectListDateWithLimit(5);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public void insertTest() throws Exception {
        List<GPSPoint> list = GPSPointExtractor.extractFromFile("D:\\GR\\Geolife Trajectories 1.3\\Geolife Trajectories 1.3\\Data\\000\\Trajectory\\20081214011036.plt");
        for (GPSPoint point : list) {
            point.setUserId("000");
            insert(point);
        }
    }

    public void insert(GPSPoint point) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO gpspoints_geolife(user_id,date,time,lng,lat) VALUES ( ?, ?, ?, ?, ?)";
        try {
            String[] s = point.getTime().split("\\s");
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            // Parameters start with 1
            preparedStatement.setString(1, point.getUserId());
            preparedStatement.setString(2, s[0]);
            preparedStatement.setString(3, s[1]);
            preparedStatement.setDouble(4, point.getLng());
            preparedStatement.setDouble(5, point.getLat());

            int result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<GPSPoint> selectAll() {
        List<GPSPoint> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM gpspoints_geolife";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                GPSPoint obj = new GPSPoint();
                obj.setUserId(rs.getString("user_id"));
                obj.setLat(rs.getDouble("lat"));
                obj.setLng(rs.getDouble("lng"));
                obj.setTime(rs.getString("date") + " " + rs.getString("time"));

                list.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Statement Error");
        }
        return list;
    }

    /**
     * select point by date
     *
     * @param date
     * @return
     */
    public List<GPSPoint> selectByDate(String date) {
        List<GPSPoint> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM gpspoints_geolife WHERE date=?";

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GPSPoint obj = new GPSPoint();
                obj.setUserId(rs.getString("user_id"));
                obj.setLat(rs.getDouble("lat"));
                obj.setLng(rs.getDouble("lng"));
                obj.setTime(rs.getString("date") + " " + rs.getString("time"));

                list.add(obj);
            }
        } catch (SQLException ex) {
            System.out.println("Statement Error");
        }
        return list;
    }

    /**
     * select n last date
     *
     * @param n number date
     * @return list dates as list of String
     */
    public List<String> selectListDateWithLimit(int n) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT date FROM gpspoints_geolife WHERE 1 GROUP by date DESC limit ?";
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String s = rs.getString("date");
                list.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Statement Error");
        }
        return list;
    }

    /**
     * select all dates
     *
     * @return dates as list of String
     */
    public List<String> selectListDate() {
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT date FROM gpspoints_geolife GROUP BY date";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String s = rs.getString("date");
                list.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Statement Error");
        }
        return list;
    }

}
