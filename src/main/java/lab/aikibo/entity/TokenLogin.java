package lab.aikibo.entity;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by tamami on 02/02/17.
 */
public class TokenLogin extends org.springframework.security.core.userdetails.User {

    private DatLogin datLogin;

    public TokenLogin(DatLogin datLogin) {
        super(datLogin.getNmLogin(), datLogin.getPassword(),
                AuthorityUtils.createAuthorityList(datLogin.getNmLogin().toString()));
        this.datLogin = datLogin;
    }

    public DatLogin getDatLogin() {
        return datLogin;
    }

    public String getNmLogin() {
        return datLogin.getNmLogin();
    }

}
