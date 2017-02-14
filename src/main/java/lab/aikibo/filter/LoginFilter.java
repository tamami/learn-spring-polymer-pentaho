package lab.aikibo.filter;

import lab.aikibo.entity.TokenLogin;
import lab.aikibo.services.TokenAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tamami on 03/02/17.
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private TokenAuthService tokenAuthService;

    public LoginFilter(String urlMapping, AuthenticationManager authManager, TokenAuthService tokenAuthService) {
        super(new AntPathRequestMatcher(urlMapping));
        setAuthenticationManager(authManager);
        this.tokenAuthService = tokenAuthService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(
                   request.getParameter("username"), request.getParameter("password")
                ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication auth)
            throws IOException, ServletException {
        tokenAuthService.addAuthentication(response, (TokenLogin) auth.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
