package com.spooky.pizza;


import com.spooky.pizza.model.Order;
import com.spooky.pizza.service.DefaultDataGenerator;
import com.spooky.pizza.service.OrderService;

public class PizzaApplication {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        Order order1 = DefaultDataGenerator.createOrder();
        orderService.saveReport(order1);
    }
}
