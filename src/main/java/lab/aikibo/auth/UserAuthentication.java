package lab.aikibo.auth;

import lab.aikibo.entity.TokenLogin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by tamami on 03/02/17.
 */
public class UserAuthentication implements Authentication {

    private final TokenLogin login;
    private boolean authenticated = true;

    public UserAuthentication(TokenLogin login) {
        this.login = login;
    }

    @Override
    public String getName() {
        return login.getNmLogin();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return login.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return login.getPassword();
    }

    @Override
    public TokenLogin getDetails() {
        return login;
    }

    @Override
    public Object getPrincipal() {
        return login.getNmLogin();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

}
