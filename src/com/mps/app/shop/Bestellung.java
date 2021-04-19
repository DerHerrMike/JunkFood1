package com.mps.app.shop;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bestellung {

    private List<JunkFood> order = new ArrayList<>();
    private double gross = 0.0f;
    private final double minimumDeliveryAmount = 14.90;


    public Bestellung() {
    }

    public Bestellung(List<JunkFood> order, double gross) {
        this.order = order;
        this.gross = gross;
    }


    public void menu(Burger burger, Pizza pizza, HotDog hotdog, List<JunkFood> burgerList, List<JunkFood> pizzaList, List<JunkFood> hotDogList) {

        System.out.println("Willkommen bei MegaMike - fettiger geht nimma!");
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
                    System.out.println("Ungültige Auswahl getroffen! Weiter mit Enter!");
                    scanner.nextLine();
                    System.out.println();
                }
            }
            switch (auswahl) {      //TODO this is weird! first item in list is never found, others are!
                case 1 -> {
                    boolean available = false;
                    while (!available) {

                        System.out.println("Bitte den Namen des Burgers eingeben: ");
                        String burgerselected = scanner.nextLine();
                        for (JunkFood junkFood : burgerList) {
                            if (junkFood instanceof Burger) {

                                if (burgerselected.equalsIgnoreCase(junkFood.getName())) {
                                    setOrder(order, junkFood);
                                    addGross(junkFood.getPrice());
                                    System.out.println("Burger " + burgerselected + " wurde deiner Bestellung hinzugefügt!");
                                    System.out.println();
                                    available = true;
                                    break;
                                }
                            } else {
                                System.out.println("Wrong JunkFood Item in Burger List!");
                            }
                        }
                        if (available) {
                            break;
                        } else {
                            System.out.println("Fehlerhafte Eingabe, bitte nochmals versuchen. Abbruch mit X, weiter mit Enter!");
                            String exit = scanner.nextLine();
                            if (exit.equalsIgnoreCase("x")) {
                                System.out.println("Programm wird beendet!");
                                System.exit(0);
                            }
                        }
                    }
                }
                case 2 -> {
                    boolean available = false;
                    while (!available) {

                        System.out.println("Bitte den Namen der Pizza eingeben: ");
                        String pizzaselected = scanner.nextLine();
                        for (JunkFood junkFood : pizzaList) {
                            if (junkFood instanceof Pizza) {

                                if (pizzaselected.equalsIgnoreCase(junkFood.getName())) {
                                    setOrder(order, junkFood);
                                    addGross(junkFood.getPrice());
                                    System.out.println("Pizza " + pizzaselected + " wurde deiner Bestellung hinzugefügt!");
                                    System.out.println();
                                    available = true;
                                    break;
                                }
                            } else {
                                System.out.println("Wrong JunkFood Item in Pizza List!");
                            }
                        }
                        if (available) {
                            break;
                        } else {
                            System.out.println("Fehlerhafte Eingabe, bitte nochmals versuchen. Abbruch mit X, weiter mit Enter!");
                            String exit = scanner.nextLine();
                            if (exit.equalsIgnoreCase("x")) {
                                System.out.println("Programm wird beendet!");
                                System.exit(0);
                            }
                        }
                    }
                }
                case 3 -> {
                    boolean available = false;
                    while (!available) {

                        System.out.println("Bitte den Namen des HotDogs eingeben: ");
                        String hotdogselected = scanner.nextLine();
                        for (JunkFood junkFood : hotDogList) {
                            if (junkFood instanceof HotDog) {

                                if (hotdogselected.equalsIgnoreCase(junkFood.getName())) {
                                    setOrder(order, junkFood);
                                    addGross(junkFood.getPrice());
                                    System.out.println("HotDog "+ hotdogselected+" wurde deiner Bestellung hinzugefügt!");
                                    System.out.println();
                                    available = true;
                                    break;
                                }
                            } else {
                                System.out.println("Wrong JunkFood Item in HotDog List");
                            }
                        }
                        if (available) {
                            break;
                        } else {
                            System.out.println("Fehlerhafte Eingabe, bitte nochmals versuchen. Abbruch mit X, weiter mit Enter!");
                            String exit = scanner.nextLine();
                            if (exit.equalsIgnoreCase("x")) {
                                System.out.println("Programm wird beendet!");
                                System.exit(0);
                            }
                        }
                    }
                }

                case 9 -> furtherItemOrder = false;
                default -> throw new IllegalStateException("Ungültige Auswahl: " + auswahl);
            }

            System.out.println("Weiteres fettiges JunkFood bestellen (j/n)");
            String further = scanner.nextLine();
            if (further.equalsIgnoreCase("n")) {
                furtherItemOrder = false;
            }
        }
        return order;
    }

    //G & S


    public double getGross() {
        return gross;
    }

    public void addGross(double gross) {
    this.gross = getGross()+gross;
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
