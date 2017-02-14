package lab.aikibo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lab.aikibo.auth.UserAuthentication;
import lab.aikibo.entity.DatLogin;
import lab.aikibo.entity.TokenLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by tamami on 03/02/17.
 */
@Service
public class TokenAuthService {

    private static final long VALIDITY_TIME_MS = 10 * 24 * 60 * 60 * 1000; // 10  days
    private static final String AUTH_HEADER_NAME = "x-auth-token";

    @Value("${token.secret}")
    private String secret;

    public String addAuthentication(HttpServletResponse response, TokenLogin tokenLogin) {
        String token = createTokenForUser(tokenLogin.getDatLogin());
        response.addHeader(AUTH_HEADER_NAME, token);
        return token;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if(token != null && !token.isEmpty()) {
            final TokenLogin login = parseUserFromToken(token);
            if(login != null) {
                return new UserAuthentication(login);
            }
        }
        return null;
    }

    public TokenLogin parseUserFromToken(String token) {
        String userJSON = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return new TokenLogin(fromJSON(userJSON));
    }

    public String createTokenForUser(DatLogin login) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .setSubject(toJSON(login))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private DatLogin fromJSON(final String userJSON) {
        try {
            return new ObjectMapper().readValue(userJSON, DatLogin.class);
        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String toJSON(DatLogin login) {
        try {
            return new ObjectMapper().writeValueAsString(login);
        } catch(JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
