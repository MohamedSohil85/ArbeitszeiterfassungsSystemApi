package org.mohamed.dto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "sickness")
public class Inability extends Vacation{
}
