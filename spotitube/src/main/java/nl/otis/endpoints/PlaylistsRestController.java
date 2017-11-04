package nl.otis.endpoints;

import nl.otis.domain.Playlists;
import nl.otis.domain.TrackDm;
import nl.otis.dto.PlaylistsRequestBody;
import nl.otis.dto.PlaylistsResponseBody;
import nl.otis.dto.TracksRequestBody;
import nl.otis.dto.TracksResponseBody;
import nl.otis.services.TokenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("playlists")
public class PlaylistsRestController {
    @Inject
    TokenService tokenservice;

    @Inject
    Playlists playlists;

    @Inject
    TrackDm trackDm;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlistData(
            @QueryParam("token") String token
    ) {
        if(!tokenservice.checkToken(token)){return javax.ws.rs.core.Response.status(300).build();}

        PlaylistsResponseBody responseBody = new PlaylistsResponseBody();
        responseBody.setPlaylists(playlists.retrievePlaylists());

        return getReturnOkPlaylists(responseBody);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistsRequestBody requestBody,
                                @QueryParam("token") String token){
        if(!tokenservice.checkToken(token)){return javax.ws.rs.core.Response.status(500).build();}

        playlists.addPlaylist(requestBody);

        PlaylistsResponseBody responseBody = new PlaylistsResponseBody();
        responseBody.setPlaylists(playlists.retrievePlaylists());

        return getReturnOkPlaylists(responseBody);
    }
    @DELETE
    @Path("/{pid}/tracks/{tid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTracks(
            @QueryParam("token") String token,
            @PathParam("pid") int playlistId,
            @PathParam("tid") int trackId

    ) {
        if(!tokenservice.checkToken(token)){return javax.ws.rs.core.Response.status(500).build();}

        trackDm.deleteTrack(trackId,playlistId);
        TracksResponseBody responseBody = new TracksResponseBody();
        responseBody.setTracks(trackDm.retrieveTracks(playlistId,true));
        return getReturnOkTracks(responseBody);
    }

    @POST
    @Path("/{pid}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTracks(
            TracksRequestBody requestBody,
            @QueryParam("token") String token,
            @PathParam("pid") int playlistId
    ) {
        if(!tokenservice.checkToken(token)){return javax.ws.rs.core.Response.status(500).build();}

        trackDm.addTrack(requestBody.getId(),playlistId,requestBody.getOfflineAvailable());

        TracksResponseBody responseBody = new TracksResponseBody();
        responseBody.setTracks(trackDm.retrieveTracks(playlistId,true));
        return getReturnOkTracks(responseBody);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlistDelete(
            @QueryParam("token") String token,
            @PathParam("id") String id
    ) {
        if(!tokenservice.checkToken(token)){return javax.ws.rs.core.Response.status(500).build();}
        playlists.deletePlaylist(id);

        PlaylistsResponseBody responseBody = new PlaylistsResponseBody();
        responseBody.setPlaylists(playlists.retrievePlaylists());

        return getReturnOkPlaylists(responseBody);

    }
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alterPlaylist(
            PlaylistsRequestBody requestBody,
            @QueryParam("token") String token,
            @PathParam("id") String id
    ) {
        if(!tokenservice.checkToken(token)){return Response.status(500).build();}
        playlists.changeName(requestBody,id);

        PlaylistsResponseBody responseBody = new PlaylistsResponseBody();
        responseBody.setPlaylists(playlists.retrievePlaylists());

        return getReturnOkPlaylists(responseBody);
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracksDataInPL(
            @QueryParam("token") String token,
            @PathParam("id") int id

    ) {
        if(!tokenservice.checkToken(token)){return javax.ws.rs.core.Response.status(500).build();}

        TracksResponseBody responseBody = new TracksResponseBody();
        responseBody.setTracks(trackDm.retrieveTracks(id,true));

        return getReturnOkTracks(responseBody);
    }
    private Response getReturnOkPlaylists(PlaylistsResponseBody responseBody) {
        return Response.status(Response
                .Status.OK)
                .entity(responseBody).build();
    }
    private Response getReturnOkTracks(TracksResponseBody responseBody) {
        return Response.status(Response
                .Status.OK)
                .entity(responseBody).build();
    }
}
