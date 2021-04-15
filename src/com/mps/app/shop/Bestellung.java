package com.mps.app.shop;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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


    public void menu(Burger burger, Pizza pizza, HotDog hotdog, List<JunkFood> burgerList, List<JunkFood> pizzaList, List<JunkFood> hotDogList) {

        System.out.println("Willkommen bei MegaMike - fettige geht nimma!");
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
        System.out.println("---------------------------------");
    }

    public List<JunkFood> ordering(List<JunkFood> burgerList, List<JunkFood> pizzaList, List<JunkFood> hotDogList) {
        Scanner scanner = new Scanner(System.in);
        boolean furtherItemOrder = true;
        while (furtherItemOrder) {
            System.out.println();
            int auswahl = 0;
            boolean correctSelection = false;
            while (!correctSelection) {
                System.out.println("DEINE BESTELLUNG:");
                System.out.println();
                System.out.println("Was möchtest du bestellen? Wähle 1 für Burger, 2 für Pizza, 3 für HotDog oder 9 für Beenden: ");
                String selection = scanner.nextLine();
                if (selection.equals("1") || selection.equals("2") || selection.equals("3") || selection.equals("9")) {
                    auswahl = Integer.parseInt(selection);
                    correctSelection = true;
                } else {
                    System.out.println("Ungültige Auswahl getroffen! Weiter mit beliebiger Taste!");
                    scanner.nextLine();
                    System.out.println();
                }
            }
            switch (auswahl) {
                case 1 -> {
                    boolean available = false;
                    while (!available) {
                        System.out.println("Bitte den Namen des Burgers eingeben: ");
                        String burgerselected = scanner.nextLine();
                        if (burgerselected.equalsIgnoreCase(burgerList.get(0).getName())) {
                            setOrder(order, burgerList.get(0));
                            available = true;
                        } else {
                            System.out.println("Fehlerhafte Eingabe, bitte nochmals versuchen: ");
                        }

                    }
                }
                case 2 -> {
                    boolean available = false;
                    while (!available) {
                        System.out.println("Bitte den Namen der Pizza eingeben: ");
                        String pizzaselected = scanner.nextLine();
                        if (pizzaselected.equalsIgnoreCase(pizzaList.get(0).getName())) {
                            setOrder(order, pizzaList.get(0));
                            available = true;
                        } else {
                            System.out.println("Fehlerhafte Eingabe, bitte nochmals versuchen: ");
                        }
                    }
                }
                case 3 -> {
                    boolean available = false;
                    while (!available) {
                        System.out.println("Bitte den Namen des HotDogs eingeben: ");
                        String hotdogselected = scanner.nextLine();
                        if (hotdogselected.equalsIgnoreCase(hotDogList.get(0).getName())) {
                            setOrder(order, pizzaList.get(0));
                            available = true;
                        } else {
                            System.out.println("Fehlerhafte Eingabe, bitte nochmals versuchen: ");
                        }
                    }
                }

                case 9 -> furtherItemOrder = false;
                default -> throw new IllegalStateException("Ungültige Auswahl: " + auswahl);
            }
            System.out.println();
            System.out.println("Weiteres fettiges JunkFood bestellen (j/n)");
            String further = scanner.nextLine();
            if (further.equalsIgnoreCase("n")){
                furtherItemOrder = false;
            }
        }
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
