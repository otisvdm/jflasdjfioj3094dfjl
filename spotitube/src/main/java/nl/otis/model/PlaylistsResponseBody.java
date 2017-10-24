package nl.otis.model;

import nl.otis.Functions.DBconnect;
import nl.otis.Objects.Playlist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistsResponseBody {

    private List<Playlist> playlists;
    private int length = 20;

    public PlaylistsResponseBody() {
        playlists = retrievePlaylists();
    }

    private List<Playlist> retrievePlaylists() {
        DBconnect database = new DBconnect();
        Connection connection = database.getConnection();
        List<Playlist> result = new ArrayList<>();
        try{
            Statement stmt=connection.createStatement();
            String sql="SELECT * FROM playlists";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id"));
                playlist.setName(rs.getString("name"));
                playlist.setOwner(rs.getBoolean("owner"));
                playlist.setTracks(Collections.EMPTY_LIST);
                playlist.setLength(20);
                result.add(playlist);
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return result;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
