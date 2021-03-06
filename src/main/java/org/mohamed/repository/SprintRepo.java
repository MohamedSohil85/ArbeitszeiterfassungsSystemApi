package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.Sprint;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SprintRepo implements PanacheRepository<Sprint> {
}
