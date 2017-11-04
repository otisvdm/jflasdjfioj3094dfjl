package nl.otis.services;

import javax.ejb.Singleton;
import java.security.SecureRandom;

@Singleton
public class TokenService {
    private String username;
    private String token;

    public void generateToken(String username) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        token = bytes.toString();
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public boolean checkToken(String token) {
        return this.token.equals(token);
    }
}
