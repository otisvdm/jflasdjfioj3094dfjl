package nl.otis.model;

import nl.otis.Functions.DBconnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginRequestBody {

    private String user;
    private String password;

    private DBconnect database = new DBconnect();
    private Connection connection = database.getConnection();

    public boolean login() {
        String result = null;
        try{
            Statement stmt=connection.createStatement();
            String sql="SELECT username FROM users WHERE username = '"+user+"' AND password = '"+password+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                result = rs.getString("username");
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        if(result != null) {
            return true;
        }
        else {
            return false;
        }
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
