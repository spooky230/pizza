package com.spooky.pizza.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
@XmlAccessorType(XmlAccessType.PROPERTY)
public enum Amount {
    NONE(0),
    LESS(0.5),
    MEDIUM(1),
    MORE(1.5),
    A_LOT(2);

    private final double priceMultiplier;

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    Amount(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
}
