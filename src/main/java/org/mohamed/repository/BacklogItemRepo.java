package org.mohamed.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.mohamed.dto.BacklogItem;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BacklogItemRepo implements PanacheRepository<BacklogItem> {
}
