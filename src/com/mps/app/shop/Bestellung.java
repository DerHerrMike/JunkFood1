package com.mps.app.shop;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;

import java.util.List;

public class Bestellung {

    private List<JunkFood> order;
    private double gross;
    private final double minimumDeliveryAmount = 14.90;


    public Bestellung() {
    }

    public Bestellung(List<JunkFood> order, double gross) {
        this.order = order;
        this.gross = gross;
    }



    public List<JunkFood> menu(List<Burger> burgerList, List<Pizza> pizzaList, List<HotDog> hotDogList) {

        System.out.println("Willkommen bei MegaMike");
        System.out.println();
        System.out.println("Verfügbare Produkte: ");
        System.out.println();
        System.out.println("BURGER:");

        for (int i = 0; i< burgerList.size(); i++){
           String burger = burgerList.get(i).toString();
            System.out.println(burger);
        }
        System.out.println();
        System.out.println("PIZZEN:");
        for (int i = 0; i<pizzaList.size(); i++){
            String pizza =  pizzaList.get(i).toString();
            System.out.println(pizza);
        }
        System.out.println();
        System.out.println("HOTDOGS:");
        for (int i = 0; i<hotDogList.size(); i++){
            String hotDog =             hotDogList.get(i).toString();
            System.out.println(hotDog);
        }
        System.out.println();
        System.out.println();
        order.add(burgerList.get(0));
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

    public void setOrder(List<JunkFood> order) {
        this.order = order;
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
