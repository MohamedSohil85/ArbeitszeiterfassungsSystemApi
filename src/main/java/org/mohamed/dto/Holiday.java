package org.mohamed.dto;

import org.mohamed.model.ResponseOfHoliRequest;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value = "Holiday")
public class Holiday extends Vacation{
    @Enumerated(EnumType.STRING)
    private ResponseOfHoliRequest status;

}
