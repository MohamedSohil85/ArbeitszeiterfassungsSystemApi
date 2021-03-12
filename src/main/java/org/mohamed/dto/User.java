package org.mohamed.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@UserDefinition
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User extends PanacheEntity {

    private String lastName;
    private String firstName;
    private String email;
    @Username
    private String userName;
    @Password
    private String password;
    private String token;
    @ManyToOne
    private Sprint sprint;
    @Roles
    @OneToMany
    @JsonIgnore
    private List<Role> roles;

}
