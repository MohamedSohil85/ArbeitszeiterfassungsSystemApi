package org.mohamed.dto;

import io.netty.util.internal.EmptyPriorityQueue;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mohamed.model.Priority;
import org.mohamed.model.WorkStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BacklogItem extends PanacheEntity {

    private String itemName;
    private String description;
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @OneToOne
    private Timeblock timeblock;

    @OneToOne
    private User user;


}
