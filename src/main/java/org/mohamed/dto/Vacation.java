package org.mohamed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Vacation extends PanacheEntity {

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss",timezone = "Europa/Berlin")
    private LocalDateTime timeStart;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss",timezone = "Europa/Berlin")
    private LocalDateTime timeEnd;
}
