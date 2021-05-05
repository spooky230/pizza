package com.spooky.pizza.model.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@XmlType
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
public class ComponentMapDto {
    @XmlElement(name = "Component")
    private List<ComponentDto> components = new ArrayList<>();
}
