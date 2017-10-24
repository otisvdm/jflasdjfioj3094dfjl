package nl.otis.Endpoints;

import nl.otis.model.TracksResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class EndTracks {
    @GET
    @Path("tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tracksData(
            @QueryParam("token") String token,
            @QueryParam("forPlaylist") int playlistId
    ) {
        TracksResponseBody responseBody = new TracksResponseBody(playlistId);
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response
                .Status.OK)
                .entity(responseBody).build();
    }
}