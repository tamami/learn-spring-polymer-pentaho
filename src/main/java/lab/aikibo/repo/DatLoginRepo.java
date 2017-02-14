package lab.aikibo.repo;

import lab.aikibo.entity.DatLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by tamami on 02/02/17.
 */
public interface DatLoginRepo extends JpaRepository<DatLogin, String> {

    Optional<DatLogin> findOneByNmLogin(String nmLogin);

}
