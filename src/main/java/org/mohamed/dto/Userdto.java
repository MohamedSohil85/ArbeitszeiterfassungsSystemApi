package org.mohamed.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.*;
import org.mohamed.model.MemberStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@UserDefinition
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Userdto extends PanacheEntity {

    private String lastName;
    private String firstName;
    private String email;
    @Username
    private String userName;
    @Password
    private String password;
    private String token;
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
    private String typeOfEmpolyment;
    @ManyToOne
    private Project project;
    @OneToOne
    private Vacation vacation;
    @OneToMany
    private List<Timeblock>timeblocks;
    @Roles
    @OneToMany
    @JsonIgnore
    private List<Role> roles;

}
