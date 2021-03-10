package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.Holiday;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HolidayRepository implements PanacheRepository<Holiday> {
}
