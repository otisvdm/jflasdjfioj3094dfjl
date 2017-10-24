package nl.otis.endpoints.dto;

import nl.otis.functions.DBconnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistsResponseBody {

    private List<PlaylistsRequestBody> playlists;
    private int length = 20;

    public PlaylistsResponseBody() {
        playlists = retrievePlaylists();
    }

    private List<PlaylistsRequestBody> retrievePlaylists() {
        DBconnect database = new DBconnect();
        Connection connection = database.getConnection();
        List<PlaylistsRequestBody> result = new ArrayList<>();
        try{
            Statement stmt=connection.createStatement();
            String sql="SELECT * FROM playlists";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                PlaylistsRequestBody playlistsRequestBody = new PlaylistsRequestBody();
                playlistsRequestBody.setId(rs.getInt("id"));
                playlistsRequestBody.setName(rs.getString("name"));
                playlistsRequestBody.setOwner(rs.getBoolean("owner"));
                playlistsRequestBody.setTracks(Collections.EMPTY_LIST);
                playlistsRequestBody.setLength(20);
                result.add(playlistsRequestBody);
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

    public List<PlaylistsRequestBody> getPlaylistsRequestBodies() {
        return playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
