package nl.otis.dao;

import nl.otis.dto.TracksRequestBody;
import nl.otis.services.DBconnect;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TracksDao {
    @Inject
    DBconnect database;

    public List<TracksRequestBody> addTrack(int trackId, int playlistId,boolean offlineAvailable) {
        String sql= "INSERT INTO `Spotitube`.`trackInPlaylist`(`trackId`, `playlistId`,`offlineAvailable`)VALUES(\""+trackId+"\","+playlistId+","+offlineAvailable+")";
        return executeUpdateDatabase(playlistId,true,sql);
    }
    public List<TracksRequestBody> deleteTrack(int trackId,int playlistId) {
        String sql= "DELETE FROM trackInPlaylist WHERE trackId = "+trackId+" AND playlistId = "+playlistId;
        return executeUpdateDatabase(playlistId,true,sql);
    }

    private List<TracksRequestBody> executeUpdateDatabase(int playlistId,boolean inPlaylist,String sql) {
        Connection connection = database.getConnection();
        try {
            Statement stmt=connection.createStatement();
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return retrieveTracks(playlistId,true);
    }


    public List<TracksRequestBody> retrieveTracks(int playlistId,boolean inPlaylist) {
        Connection connection = database.getConnection();

        List<TracksRequestBody> result = new ArrayList<>();
        try{
            Statement stmt=connection.createStatement();
            String sql;
            if(!inPlaylist) {
                sql =   "SELECT id,title,performer,duration,album,playcount,publicationDate,description,offlineAvailable " +
                        "FROM tracks " +
                        "LEFT JOIN trackinplaylist " +
                        "ON tracks.id = trackinplaylist.trackId " +
                        "AND trackinplaylist.playlistId = "+playlistId+" " +
                        "WHERE id NOT IN(SELECT trackId FROM trackInPlaylist WHERE playlistId = "+playlistId+")";
            }
            else {
                sql =   "SELECT id,title,performer,duration,album,playcount,publicationDate,description,offlineAvailable " +
                        "FROM tracks " +
                        "LEFT JOIN trackinplaylist " +
                        "ON tracks.id = trackinplaylist.trackId " +
                        "AND trackinplaylist.playlistId = "+playlistId+" " +
                        "WHERE id IN(SELECT trackId FROM trackInPlaylist WHERE playlistId = "+playlistId+")";
            }
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                TracksRequestBody track = new TracksRequestBody();
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
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
