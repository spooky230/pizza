package com.spooky.pizza.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"orderedTime","deliveredTime","client","pizzaMap"})
public class Order {
    @XmlElement
    private Map<Pizza,Integer> pizzaMap;
    @XmlElement
    private Client client;
    @XmlElement
    private Date orderedTime;
    @XmlElement
    private Date deliveredTime;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    public Order(){
        this.orderedTime = new Date();
        this.pizzaMap = new HashMap<>();
    }
    public void setOrderedTime(Date orderedTime) {
        this.orderedTime = orderedTime;
    }

    public void setDeliveredTime(Date deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    @Builder
    public Order(Client client){
        this();
        this.client = client;
    }
    public void addPizza(Pizza pizza){
        addPizza(pizza,1);
    }
    public void addPizza(Pizza pizza,int count){
        if(pizzaMap.containsKey(pizza)){
            Integer pizzaCount = pizzaMap.get(pizza);
            pizzaCount += count;
            pizzaMap.replace(pizza,pizzaCount);
        }
        else{
            pizzaMap.put(pizza,count);
        }
    }
    public void setOrderedTime(String time){
        try {
            this.orderedTime = DATE_FORMAT.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setDeliveredTime(String time){
        try {
            this.deliveredTime = DATE_FORMAT.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
