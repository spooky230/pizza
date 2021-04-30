package com.spooky.pizza.service;

import com.spooky.pizza.model.Amount;
import com.spooky.pizza.model.Component;
import com.spooky.pizza.model.Order;
import com.spooky.pizza.model.Pizza;
import org.apache.commons.lang3.StringUtils;


import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class OrderService {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("y.MM.dd  HH:mm:ss");
    public static final SimpleDateFormat REPORT_NAME_FORMAT = new SimpleDateFormat("y-MM-dd  HH-mm-ss");
    public static final String PIZZA_NAME = "|%s|%n";
    public static final String PIZZA_NAME_HEADER = "PIZZA: %s x %d";
    public static final String NAME_FORMAT = "\"%s\"";
    public static final String TOP_BORDER = "____________________________________________________";
    public static final String HEADER = "|                      PIZZA ORDER                 |";
    public static final String EMPTY_LINE = "|                                                  |";
    public static final String MIDDLE_BORDER = "|__________________________________________________|";
    public static final String ORDER_DATE = "| Order date    :  %-32s|%n";
    public static final String DELIVERY_DATE = "| Delivery date :  %-32s|%n";
    public static final String CLIENT_NAME = "| Client  :    %-36.34s|%n";
    public static final String CLIENT_PHONE = "| Phone   :    %-36s|%n";
    public static final String CLIENT_ADDRESS = "| Address :    %-36.34s|%n";
    public static final String COMPONENT = "|  %-15s%-17s%8.2f x %3s  |%n";
    public static final String TOTAL_PRICE = "| Total price:                         $%9.2f  |%n";
    public static final String PER_PIZZA = "|  Price per %-38.34s|%n";
    public static final String  PRICE_PER_PIZZA= "|%50s|%n";
    public static final String  PRICE_FORMAT= "$%-8.2f";

    public String prepareOrder(Order order) {
        String report = TOP_BORDER;
        report += System.lineSeparator();
        report += HEADER + System.lineSeparator();
        report += EMPTY_LINE + System.lineSeparator();
        report += String.format(ORDER_DATE, DATE_FORMAT.format(order.getOrderedTime()));
        report += String.format(DELIVERY_DATE, DATE_FORMAT.format(order.getDeliveredTime()));
        report += EMPTY_LINE + System.lineSeparator();
        report += String.format(CLIENT_NAME, order.getClient().getName());
        report += String.format(CLIENT_PHONE, order.getClient().getPhoneNumber());
        report += String.format(CLIENT_ADDRESS, order.getClient().getAddress());
        report += EMPTY_LINE + System.lineSeparator();
        double totalPrice = 0;
        for (Map.Entry<Pizza, Integer> entryOuter : order.getPizzaMap().entrySet()) { //Cycle for each pizza in pizzaMap
            String formatedPizzaName = String.format(NAME_FORMAT, entryOuter.getKey().getName());
            String formattedPizzaHeader = String.format(PIZZA_NAME_HEADER,formatedPizzaName,entryOuter.getValue());
            String center = StringUtils.center(formattedPizzaHeader,50);
            report += String.format(PIZZA_NAME, center);
            report += EMPTY_LINE + System.lineSeparator();
            report += EMPTY_LINE + System.lineSeparator();
            ///////////////////////////////////////////////////////////////////////////////////////////st
            double cash = entryOuter.getKey().getBasePrice();
            for (Map.Entry<Component, Amount> entry : entryOuter.getKey().getComponentsMap().entrySet()) {//Cycle for all components in pizza of Cycle above
                report += String.format(COMPONENT, entry.getKey().getName(), entry.getValue(), entry.getKey().getPrice(), entry.getValue().getPriceMultiplier());
                cash += (entry.getValue().getPriceMultiplier() * entry.getKey().getPrice());
            }
            totalPrice += cash*entryOuter.getValue();
//        report += "|  Tomato sauce    Medium              2,30 x 1,0  |"+ System.lineSeparator();
//        report += "|  Hen             Less               15,00 x 0,5  |"+ System.lineSeparator();
//        report += "|  Onion           A lot               3,12 x 2,0  |"+ System.lineSeparator();
            ///////////////////////////////////////////////////////////////////////////////////////////end
            report += String.format(PER_PIZZA, formatedPizzaName);
            String formatedPrice = String.format(PRICE_FORMAT,cash);
            report += String.format(PRICE_PER_PIZZA,StringUtils.leftPad(formatedPrice,50));
            report += EMPTY_LINE + System.lineSeparator();
        }
        report += MIDDLE_BORDER + System.lineSeparator();
        report += String.format(TOTAL_PRICE, totalPrice);//todo:: fix price per pizza and total price calculation
        report += MIDDLE_BORDER + System.lineSeparator();
        return report;
    }

    public void printReport(String report) {
        System.out.println(report);
    }

    public void writeReport(String report, Date date) {
        String FILE = "report_" + REPORT_NAME_FORMAT.format(date) + ".txt";
        try {
            FileWriter writer = new FileWriter(FILE);
            writer.write(report);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveReport(Order order) {
        String report = prepareOrder(order);
        printReport(report);
        writeReport(report, order.getOrderedTime());
        new XmlOrderService().saveOrder(order,"order.xml");

    }
}
