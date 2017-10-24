package nl.otis.endpoints;

import nl.otis.functions.DBconnect;
import nl.otis.endpoints.dto.PlaylistsRequestBody;
import nl.otis.endpoints.dto.PlaylistsResponseBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


@Path("/")
public class PlaylistsRestController {
    @GET
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlistData(
            @QueryParam("token") String token
    ) {
        PlaylistsResponseBody responseBody = new PlaylistsResponseBody();

        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();
    }

    @POST
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistsRequestBody requestBody){
        DBconnect database = new DBconnect();
        Connection connection = database.getConnection();
        try{
            Statement stmt=connection.createStatement();
            String sql= "INSERT INTO `Spotitube`.`playlists`(`name`, `owner`)VALUES(\""+requestBody.getName()+"\", true)";
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        PlaylistsResponseBody responseBody = new PlaylistsResponseBody();

        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();
    }

    @DELETE
    @Path("/playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlistDelete(
            @QueryParam("token") String token,
            @PathParam("id") String id
    ) {
        DBconnect database = new DBconnect();
        Connection connection = database.getConnection();
        try{
            Statement stmt=connection.createStatement();
            String sql= "DELETE FROM trackInPlaylist WHERE playlistId = "+id;
            String sql2 = "DELETE FROM playlists WHERE id ="+id;
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        PlaylistsResponseBody responseBody = new PlaylistsResponseBody();

        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();

    }
    @PUT
    @Path("/playlists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response playlistDelete(
            PlaylistsRequestBody requestBody,
            @QueryParam("token") String token,
            @PathParam("id") String id
    ) {
        DBconnect database = new DBconnect();
        Connection connection = database.getConnection();
        try{
            Statement stmt=connection.createStatement();
            String sql= "UPDATE playlists SET name ='"+requestBody.getName()+"' WHERE Id = "+id;
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        PlaylistsResponseBody responseBody = new PlaylistsResponseBody();

        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();

    }
}
