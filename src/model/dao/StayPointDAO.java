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
import model.Coordinate;
import model.StayPoint;

/**
 *
 * @author trungtran.vn
 */
public class StayPointDAO {

    private Connection connection;

    public StayPointDAO() {
        connection = DBConnectHelper.getDBConnection();
    }

    public static void main(String[] args) {
        StayPointDAO dao = new StayPointDAO();
        dao.insertTest();
        System.out.println(dao.selectInsertId());
    }

    public void insertTest() {
        StayPoint sp = new StayPoint(new Coordinate(34.23232, 42.213131), "2014-03-13 09:33:12", "2014-03-13 09:33:12");
        System.out.println(insert(sp));
    }

    public int insert(StayPoint point) {
        PreparedStatement preparedStatement = null;
        int id = 0;
        String sql = "INSERT INTO staypoints(lat, lng, date_start, time_start, date_end, time_end, user_id) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try {
            String[] start = point.getStartTime().split("\\s");
            String[] end = point.getEndTime().split("\\s");
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            // Parameters start with 1
            preparedStatement.setDouble(1, point.getAvgCoordinate().getLat());
            preparedStatement.setDouble(2, point.getAvgCoordinate().getLng());
            preparedStatement.setString(3, start[0]);
            preparedStatement.setString(4, start[1]);
            preparedStatement.setString(5, end[0]);
            preparedStatement.setString(6, end[1]);
            preparedStatement.setString(7, point.getUserId());

            id = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            id = - 1;
        }

        return id;
    }

    public int selectInsertId() {
        PreparedStatement preparedStatement = null;
        int id = 0;
        String sql = "SELECT LAST_INSERT_ID() as id;";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            id = - 1;
        }
        return id;
    }

}
