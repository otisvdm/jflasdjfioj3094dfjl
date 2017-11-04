package nl.otis.endpoints;

import nl.otis.domain.TrackDm;
import nl.otis.dto.TracksResponseBody;
import nl.otis.services.TokenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("tracks")
public class TracksRestController {
    @Inject
    TokenService tokenservice;

    @Inject
    TrackDm trackDm;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracksDataNotInPL(
            @QueryParam("token") String token,
            @QueryParam("forPlaylist") int playlistId
    ) {
        if(!tokenservice.checkToken(token)){return javax.ws.rs.core.Response.status(500).build();}

        TracksResponseBody responseBody = new TracksResponseBody();
        responseBody.setTracks(trackDm.retrieveTracks(playlistId,false));

        return getReturnOkTracks(responseBody);
    }

    private Response getReturnOkTracks(TracksResponseBody responseBody) {
        return Response.status(Response
                .Status.OK)
                .entity(responseBody).build();
    }
}