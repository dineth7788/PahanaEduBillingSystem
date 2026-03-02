package com.pahanaedubookshop.DAO;

import com.pahanaedubookshop.Model.Admin;
import com.pahanaedubookshop.Util.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    public Admin login(String username, String password) {
        // Checking against your completely unique tbl_admin structure
        String checkAdminQuery = "SELECT * FROM tbl_admin WHERE sys_user = ? AND sys_pass = ?";

        try (Connection dbConnection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(checkAdminQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin validAdmin = new Admin();
                validAdmin.setSysId(resultSet.getInt("sys_id"));
                validAdmin.setSysUser(resultSet.getString("sys_user"));
                validAdmin.setSysPass(resultSet.getString("sys_pass"));
                return validAdmin;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}