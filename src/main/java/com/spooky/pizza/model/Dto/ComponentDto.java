package com.spooky.pizza.model.Dto;

import com.spooky.pizza.model.Amount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@NoArgsConstructor
@XmlType(propOrder = {"name","price","amount"})
@XmlAccessorType(XmlAccessType.FIELD)

public class ComponentDto {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private double price;
    @XmlAttribute
    private Amount amount;

}
