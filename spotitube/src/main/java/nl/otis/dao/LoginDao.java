package nl.otis.dao;

import nl.otis.services.DBconnect;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Default
public class LoginDao {
    @Inject
    DBconnect database;


    public boolean login(String user, String password) {
        Connection connection = database.getConnection();

        String result = null;
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT username FROM users WHERE username = '" + user + "' AND password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result = rs.getString("username");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            return true;
        } else {
            return false;
        }
    }
}
