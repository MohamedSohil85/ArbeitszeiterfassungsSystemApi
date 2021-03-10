package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.Timeblock;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TimeblockRepository implements PanacheRepository<Timeblock> {
}
