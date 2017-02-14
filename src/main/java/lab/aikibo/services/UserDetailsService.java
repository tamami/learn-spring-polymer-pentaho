package lab.aikibo.services;

import lab.aikibo.entity.DatLogin;
import lab.aikibo.entity.TokenLogin;
import lab.aikibo.repo.DatLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by tamami on 02/02/17.
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private DatLoginRepo loginRepo;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final DatLogin login = loginRepo.findOneByNmLogin(s).orElseThrow(() -> new UsernameNotFoundException(""));

        TokenLogin currentLogin = new TokenLogin(login);
        detailsChecker.check(currentLogin);

        return currentLogin;
    }
}
