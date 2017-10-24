package nl.otis.Objects;

import java.security.SecureRandom;

public class Token {
    private String token;
    public Token() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        token = bytes.toString();
    }

    public String getToken() {
        return token;
    }
}
