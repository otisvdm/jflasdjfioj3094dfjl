package nl.otis.dao;

import nl.otis.dto.PlaylistsRequestBody;
import nl.otis.dto.TracksRequestBody;
import nl.otis.services.DBconnect;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistsDao {
    @Inject
    DBconnect database;

    public List<PlaylistsRequestBody> retrievePlaylists() {
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

    public int calculateLength() {
        int length = 0;
        List<PlaylistsRequestBody> playlists = retrievePlaylists();
        for(PlaylistsRequestBody playlist : playlists) {
            TracksDao DAO = new TracksDao();
            List<TracksRequestBody> tracks = DAO.retrieveTracks(playlist.getId(),true);
            for(TracksRequestBody track : tracks) {
                length+=track.getDuration();
            }
        }
        return length;
    }

    public void deletePlaylist(String id) {
        Connection connection = database.getConnection();
        try{
            Statement stmt=connection.createStatement();
            String sql= "DELETE FROM trackInPlaylist WHERE playlistId = "+id;
            String sql2 = "DELETE FROM playlists WHERE id ="+id;
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addPlaylist(PlaylistsRequestBody requestBody) {
        Connection connection = database.getConnection();
        try{
            Statement stmt=connection.createStatement();
            String sql= "INSERT INTO `Spotitube`.`playlists`(`name`, `owner`)VALUES(\""+requestBody.getName()+"\", true)";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void changeName(PlaylistsRequestBody requestBody, String id) {
        Connection connection = database.getConnection();
        try{
            Statement stmt=connection.createStatement();
            String sql= "UPDATE playlists SET name ='"+requestBody.getName()+"' WHERE Id = "+id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
