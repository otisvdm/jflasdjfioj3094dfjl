package nl.otis.services;

import javax.ejb.Singleton;
import javax.enterprise.inject.Default;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Default
public class DBconnect {
    //  Database credentials
    Properties props = new Properties();

    //  Connection
    Connection conn = null;

    public DBconnect() {
        connect();
    }
    private void connect() {
        try{
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream resourceStream = loader.getResourceAsStream("db.properties");
            props.load(resourceStream);
            Class.forName(props.getProperty("DRIVER"));
            conn = DriverManager.getConnection(props.getProperty("DB_URL"),props.getProperty("USER"),props.getProperty("PASS"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return this.conn;
    }
}
