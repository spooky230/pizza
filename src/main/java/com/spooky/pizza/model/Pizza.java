package com.spooky.pizza.model;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name","componentsMap"})
public class Pizza {
    @NotNull
    @XmlAttribute
    private String name;
    @XmlElement
    private Map<Component,Amount> componentsMap = new HashMap<>();
    private static double basePrice = 5;

    public double getBasePrice() {
        return basePrice;
    }

    public Map<Component, Amount> getComponentsMap() {
        return componentsMap;
    }
}
