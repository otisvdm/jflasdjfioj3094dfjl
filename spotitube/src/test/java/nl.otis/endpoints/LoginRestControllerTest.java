//package nl.otis.endpoints;
//
//import nl.otis.dto.LoginRequestBody;
//import nl.otis.services.TokenService;
//import org.junit.Before;
//import org.junit.Test;
//
//
//import static org.mockito.Mockito.*;
//
//public class LoginRestControllerTest {
//
//    TokenService tokenService;
//    LoginDao loginDao;
//
//    private LoginRestController loginRestController;
//
//    @Before
//    public void setup() {
//        loginRestController = new LoginRestController();
//
//        tokenService = mock(TokenService.class);
//        loginRestController.setTokenService(tokenService);
//
//        loginDao = mock(LoginDao.class);
////        loginRestController.setLoginDao(loginDao);
//
//    }
//
//    @Test
//    public void loginShouldCallDAO() {
//        //setup
//        LoginRequestBody loginRequestBody = new LoginRequestBody();
//        loginRequestBody.setPassword("pass");
//        loginRequestBody.setUser("otisvdm");
//
//        String username = "otisvdm";
//        String password = "pass";
//
//        //test
//        loginRestController.doLogin(loginRequestBody);
//
//        //result
//        verify(loginDao).login(username, password);
//    }
//
//    @Test
//    public void loginShouldCallTokenServiceIfDaoRespondsWithTrue() {
//        //setup
//        LoginRequestBody loginRequestBody = new LoginRequestBody();
//        loginRequestBody.setPassword("pass");
//        loginRequestBody.setUser("otisvdm");
//
//        when(loginDao.login("otisvdm", "pass")).thenReturn(true);
//
//        //test
//        loginRestController.doLogin(loginRequestBody);
//
//        //result
//        verify(tokenService).getToken();
//    }
//}
