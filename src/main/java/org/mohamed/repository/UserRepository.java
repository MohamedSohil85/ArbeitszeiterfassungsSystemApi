package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
