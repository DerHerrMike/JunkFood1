package com.mps.app.shop;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bestellung {

    private List<JunkFood> order = new ArrayList<>();
    private double gross;
    private final double minimumDeliveryAmount = 14.90;


    public Bestellung() {
    }

    public Bestellung(List<JunkFood> order, double gross) {
        this.order = order;
        this.gross = gross;
    }


    public List<JunkFood> menu(Burger burger, Pizza pizza, HotDog hotdog, List<JunkFood> burgerList,  List<JunkFood> pizzaList, List<JunkFood> hotDogList) {

        System.out.println("Willkommen bei MegaMike");
        System.out.println();
        System.out.println("Verfügbare Produkte: ");
        System.out.println();

        System.out.println("BURGER:");
        burger.displayJunkFood(burgerList);
        System.out.println();

        System.out.println("PIZZEN:");
        pizza.displayJunkFood(pizzaList);
        System.out.println();

        System.out.println("HOTDOGS:");
        hotdog.displayJunkFood(hotDogList);
        System.out.println();
        System.out.println();

        setOrder(order, burgerList.get(0));
        return order;
    }

    //G & S


    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    public double getMinimumDeliveryAmount() {
        return minimumDeliveryAmount;
    }

    public List<JunkFood> getOrder() {
        return order;
    }

    public void setOrder(List<JunkFood> order, JunkFood product) {
        order.add(product);
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "order=" + order +
                ", gross=" + gross +
                ", minimumDeliveryAmount=" + minimumDeliveryAmount +
                '}';
    }
}
