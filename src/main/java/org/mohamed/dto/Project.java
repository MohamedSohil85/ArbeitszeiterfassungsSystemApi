package org.mohamed.dto;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project extends PanacheEntity {

    private String projectName;
    private String projectDescription;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @OneToMany
    private List<Sprint>sprintList;


}
