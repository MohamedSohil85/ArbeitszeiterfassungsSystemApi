package org.mohamed.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.Entity;

@Entity
@UserDefinition
public class User extends PanacheEntity {

    private String lastName;
    private String firstName;
    private String email;
    @Username
    private String userName;
    @Password
    private String password;
}
