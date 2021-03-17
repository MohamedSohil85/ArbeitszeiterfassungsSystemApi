package org.mohamed.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Vacation extends PanacheEntity {

    @OneToOne
    private Timeblock timeblock;
}
