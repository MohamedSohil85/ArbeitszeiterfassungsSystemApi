package org.mohamed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sprint extends PanacheEntity {

    private String sprintName;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Europa/Berlin")
    private Date createdDate;
    @OneToOne
    private Timeblock timeblock;
    @ManyToOne
    Project project;
    @OneToMany
    private List<BacklogItem> sprintBacklog;

}
