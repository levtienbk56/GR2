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

public class RelatedPointDAO {

    private Connection connection;

    public RelatedPointDAO() {
        connection = DBConnectHelper.getDBConnection();
    }

    public static void main(String[] args) {
        RelatedPointDAO dao = new RelatedPointDAO();
        dao.insertTest();
    }

    public void insertTest() {
        insert(1, 34.32342, 43.234);
    }

    public int insert(int staypointId, double lat, double lng) {
        PreparedStatement preparedStatement = null;
        int count = 0;
        String sql = "INSERT INTO related_points(staypoint_id, lat, lng) VALUES ( ?, ?, ?)";
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, staypointId);
            preparedStatement.setDouble(2, lat);
            preparedStatement.setDouble(3, lng);

            count = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            count = - 1;
        }

        return count;
    }

}
