package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.Inability;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SicknessRepository implements PanacheRepository<Inability> {
}
