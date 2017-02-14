package lab.aikibo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tamami on 02/02/17.
 */
@Entity
@Data
public class DatLogin {

    @Id
    private String nmLogin;
    private String nip;
    private String password;

}
