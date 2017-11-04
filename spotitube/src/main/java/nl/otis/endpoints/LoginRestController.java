package nl.otis.endpoints;

import nl.otis.dao.LoginDao;
import nl.otis.services.TokenService;
import nl.otis.dto.LoginRequestBody;
import nl.otis.dto.LoginResponseBody;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class LoginRestController {

    @Inject
    private TokenService tokenService;

    @Inject
    LoginDao loginDao;


    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin(LoginRequestBody loginRequestBody) {
        LoginResponseBody responseBody = new LoginResponseBody();

        if (!loginDao.login(loginRequestBody.getUser(),loginRequestBody.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        tokenService.generateToken(loginRequestBody.getUser());
        responseBody.setToken(tokenService.getToken());
        responseBody.setUser(loginRequestBody.getUser());
        return Response.status(Response
                .Status.OK)
                .entity(responseBody).build();
    }


    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    public void setLoginDao(LoginDao loginDao) { this.loginDao = loginDao; }
}