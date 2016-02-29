/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import database.DBConnectHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.GPSPoint;
import utils.GPSPointExtractor;

/**
 *  map với bảng gsppoints_geolife trong DB
 * @author trungtran.vn
 */
public class GPSPointTaxiDAO {

    private Connection connection;

    public GPSPointTaxiDAO() {
        connection = DBConnectHelper.getDBConnection();
    }

    public static void main(String[] args) throws Exception {
        GPSPointTaxiDAO dao = new GPSPointTaxiDAO();
        dao.insertTest();
    }

    public void selectDateTest() {
        List<String> list = selectListDateWithLimit(5);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public void insertTest() throws Exception {
        int count = 0;
        List<GPSPoint> list = GPSPointExtractor.extractFromFile("D:\\GR\\GR 2 - Sequent Pattern Mining\\TaxiDataBase\\366.txt");
        for (GPSPoint point : list) {
            insert(point);
            count++;
            if(count %500 == 0){
                System.out.println("500 next");
            }
        }
    }

    public void insert(GPSPoint point) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO gpspoints_taxi(user_id,date,time,lng,lat) VALUES ( ?, ?, ?, ?, ?)";
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
            String query = "SELECT * FROM gpspoints_taxi";
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
            String sql = "SELECT * FROM gpspoints_taxi WHERE date=?";

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
            String sql = "SELECT date FROM gpspoints_taxi WHERE 1 GROUP by date DESC limit ?";
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
            String query = "SELECT date FROM gpspoints_taxi GROUP BY date";
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
