package org.mohamed.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Holiday")
public class Holiday extends Vacation{
}
