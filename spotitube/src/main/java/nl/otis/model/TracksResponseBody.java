package nl.otis.model;
import nl.otis.Functions.DBconnect;
import nl.otis.Objects.Track;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TracksResponseBody {
    private List<Track> tracks;

    public TracksResponseBody(int playlistId) {
        tracks = retrieveTracks(playlistId);
    }

    private List<Track> retrieveTracks(int playlistId) {
        List<Track> result = new ArrayList<>();
        DBconnect database = new DBconnect();
        Connection connection = database.getConnection();
        try{
            Statement stmt=connection.createStatement();
            String sql="SELECT * FROM tracks WHERE id IN(SELECT trackId FROM trackInPlaylist WHERE playlistId !="+playlistId+")";
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
