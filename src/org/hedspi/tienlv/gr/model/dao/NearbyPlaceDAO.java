/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.model.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.hedspi.tienlv.gr.database.DBConnectHelper;
import java.sql.SQLException;

/**
 *
 * @author Lev Tien
 */
public class NearbyPlaceDAO {

    private Connection connection;

    public NearbyPlaceDAO() {
        connection = DBConnectHelper.getDBConnection();
    }

    public static void main(String[] args) {
        NearbyPlaceDAO dao = new NearbyPlaceDAO();
        dao.insertTest();
    }

    public void insertTest() {
        String placeID = "351d41bdc7cc5cdc8ced6c6450bf976e1bd11efb";
        String name = "明徳楼";
        String types = "[bus_station,transit_station,point_of_interest,establishment]";
        int spID = 1;
        insert(placeID, name, types, spID);
    }

    public int insert(String nid, String name, String types, int spId) {
        PreparedStatement preparedStatement = null;
        int id = 0;
        String sql = "INSERT INTO nearby_places(place_id, name, types, staypoint_id) VALUES ( ?, ?, ?, ?)";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, nid);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, types);
            preparedStatement.setInt(4, spId);

            id = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            id = - 1;
        }
        return id;
    }
}
