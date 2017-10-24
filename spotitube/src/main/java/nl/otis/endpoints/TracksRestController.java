package nl.otis.endpoints;

import nl.otis.endpoints.dto.TracksRequestBody;
import nl.otis.endpoints.dto.TracksResponseBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class TracksRestController {
    @GET
    @Path("tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracksDataNotInPL(
            @QueryParam("token") String token,
            @QueryParam("forPlaylist") int playlistId
    ) {
        TracksResponseBody responseBody = new TracksResponseBody(-1,"notinplaylist",playlistId);
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();
    }

    @GET
    @Path("playlists/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracksDataInPL(
            @QueryParam("token") String token,
            @PathParam("id") int id

    ) {
        TracksResponseBody responseBody = new TracksResponseBody(-1,"inplaylist",id);
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();
    }
    @DELETE
    @Path("playlists/{pid}/tracks/{tid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTracks(
            @QueryParam("token") String token,
            @PathParam("pid") int playlistId,
            @PathParam("tid") int trackId

    ) {
        TracksResponseBody responseBody = new TracksResponseBody(trackId,"deletetrack",playlistId);
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();
    }
    @POST
    @Path("playlists/{pid}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTracks(
            TracksRequestBody requestBody,
            @QueryParam("token") String token,
            @PathParam("pid") int playlistId
    ) {
        TracksResponseBody responseBody = new TracksResponseBody(requestBody.getId(),"addtoplaylist",playlistId);
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();
    }
}