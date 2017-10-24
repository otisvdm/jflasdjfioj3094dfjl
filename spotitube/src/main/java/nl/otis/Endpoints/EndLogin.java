package nl.otis.Endpoints;

import nl.otis.Objects.Token;
import nl.otis.model.LoginRequestBody;
import nl.otis.model.LoginResponseBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class EndLogin {

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginData(LoginRequestBody loginRequestBody) {
        LoginResponseBody responseBody = new LoginResponseBody();

        if(loginRequestBody.login() != false) {
            Token token = new Token();
            responseBody.setToken(token.getToken());
            responseBody.setUser(loginRequestBody.getUser());
            return Response.status(Response
                    .Status.OK)
                    .entity(responseBody).build();
        }
        else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}