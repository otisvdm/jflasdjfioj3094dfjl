package nl.otis.functions;

import java.sql.*;

public class DBconnect {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/Spotitube?useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    //  Connection
    Connection conn = null;


    public DBconnect() {
        connect();
    }
    private void connect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.conn;
    }
}
