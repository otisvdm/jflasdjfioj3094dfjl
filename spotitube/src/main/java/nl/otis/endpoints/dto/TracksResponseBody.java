package nl.otis.endpoints.dto;
import nl.otis.functions.DBconnect;
import nl.otis.domain.Track;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TracksResponseBody {
    private List<Track> tracks;

    public TracksResponseBody(int trackId,String function,int playlistId) {
        tracks = retrieveTracks(trackId,function,playlistId);
    }

    private List<Track> retrieveTracks(int trackId,String function,int playlistId) {
        List<Track> result = new ArrayList<>();
        DBconnect database = new DBconnect();
        Connection connection = database.getConnection();
        try{
            Statement stmt=connection.createStatement();
            String sql;
            if(function=="addtoplaylist") {
                sql= "INSERT INTO `Spotitube`.`trackInPlaylist`(`trackId`, `playlistId`)VALUES(\""+trackId+"\","+playlistId+")";
                stmt.executeUpdate(sql);
                sql = "SELECT * FROM tracks WHERE id IN(SELECT trackId FROM trackInPlaylist WHERE playlistId =" + playlistId + ")";
            }
            if(function=="deletetrack") {
                sql= "DELETE FROM trackInPlaylist WHERE trackId = "+trackId+" AND playlistId = "+playlistId;
                stmt.executeUpdate(sql);
                sql = "SELECT * FROM tracks WHERE id IN(SELECT trackId FROM trackInPlaylist WHERE playlistId =" + playlistId + ")";
            }
            if(function=="notinplaylist") {
                sql = "SELECT * FROM tracks WHERE id NOT IN(SELECT trackId FROM trackInPlaylist WHERE playlistId =" + playlistId + ")";
            }
            else {
                sql = "SELECT * FROM tracks WHERE id IN(SELECT trackId FROM trackInPlaylist WHERE playlistId =" + playlistId + ")";
            }
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                Track track = new Track();
                track.setId(rs.getInt("id"));
                track.setTitle(rs.getString("title"));
                track.setPerformer(rs.getString("performer"));
                track.setDuration(rs.getInt("duration"));
                track.setAlbum(rs.getString("album"));
                track.setPlaycount(rs.getInt("playcount"));
                track.setPublicationDate(rs.getDate("publicationDate"));
                track.setDescription(rs.getString("description"));
                track.setOfflineAvailable(rs.getBoolean("offlineAvailable"));
                result.add(track);
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
}
