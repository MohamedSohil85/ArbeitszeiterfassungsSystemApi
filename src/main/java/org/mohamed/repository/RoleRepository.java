package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.Role;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoleRepository implements PanacheRepository<Role> {
}
