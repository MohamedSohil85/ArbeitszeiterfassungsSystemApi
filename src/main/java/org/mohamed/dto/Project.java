package org.mohamed.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project extends PanacheEntity {

    private String projectName;
    private String projectDescription;
    @OneToMany
    private List<Sprint>sprintList;


}
