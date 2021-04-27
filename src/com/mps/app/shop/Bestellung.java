package com.mps.app.shop;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author MikeSchwingenschlögl
 * class to order available JF
 */
public class Bestellung {

    private final List<JunkFood> order = new ArrayList<>();
    private double gross = 0.0f;


    /**
     * ordering default constructor
     */
    public Bestellung() {
    }


    /**
     * displays available JF objects loaded from csv files
     * @param burger burger object loaded from csv
     * @param pizza pizza object loaded from csv
     * @param hotdog hotdog object loaded from csv
     * @param burgerList list of burgers holding loaded burgers objects
     * @param pizzaList list of pizza holding loaded pizza objects
     * @param hotDogList list of hot dog holding loaded hot dog objects
     */
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


    /**
     * method to order JF objects
     * @param burgerList list of burgers holding loaded burgers objects
     * @param pizzaList list of pizza holding loaded pizza objects
     * @param hotDogList list of hot dog holding loaded hot dog objects
     * @return List of JF containing all ordered JF items
     */
    public List<JunkFood> ordering(List<JunkFood> burgerList, List<JunkFood> pizzaList, List<JunkFood> hotDogList) {
        Scanner scanner = new Scanner(System.in);
        boolean furtherItemOrder = true;
        while (furtherItemOrder) {
            System.out.println();
            int auswahl = 0;
            boolean correctSelection = false;
            while (!correctSelection) {     // selecting burger, pizza or hot dog
                System.out.println("DEINE BESTELLUNG:");
                System.out.println();
                System.out.println("Was möchtest du bestellen? Wähle 1 für Burger, 2 für Pizza, 3 für HotDog oder 9 für Beenden: ");
                String selection = scanner.nextLine();
                if (selection.equals("1") || selection.equals("2") || selection.equals("3") || selection.equals("9")) {
                    auswahl = Integer.parseInt(selection);
                    correctSelection = true;
                } else {
                    System.out.println("Ungültige Auswahl, bitte nochmals versuchen. Abbruch mit X, weiter mit Enter!");
                    String exit = scanner.nextLine();
                    if (exit.equalsIgnoreCase("x")) {
                        System.out.println("Programm wird beendet!");
                        System.exit(0);
                    }
                }
            }
            switch (auswahl) {
                case 1 -> {
                    boolean available = false;
                    while (!available) {

                        System.out.println("Bitte den Namen des Burgers eingeben: ");   //choose burger by name
                        String burgerselected = scanner.nextLine();
                        for (JunkFood junkFood : burgerList) {
                            if (junkFood instanceof Burger) {
                                if (burgerselected.trim().equalsIgnoreCase(junkFood.getName().trim())) {
                                    setOrder(order, junkFood);
                                    addGross(junkFood.getPrice());
                                    System.out.println("Burger " + junkFood.getName() + " wurde deiner Bestellung hinzugefügt!");
                                    System.out.println();
                                    if (getGross() < getFreeDeliveryAmount()) {  // check for final amount minimum order
                                        System.out.printf("Gratis Zustellung ab EUR " + getFreeDeliveryAmount() + "! Dazu brauchst du noch EUR %.2f%n", (getFreeDeliveryAmount() - getGross()));
                                    } else {
                                        System.out.printf("Bestellwert für Gratislieferung erreicht! Aktueller Saldo EUR: %.2f%n", getGross());
                                    }
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

                        System.out.println("Bitte den Namen der Pizza eingeben: "); // choose pizza by name
                        String pizzaselected = scanner.nextLine();
                        for (JunkFood junkFood : pizzaList) {
                            if (junkFood instanceof Pizza) {

                                if (pizzaselected.equalsIgnoreCase(junkFood.getName())) {
                                    setOrder(order, junkFood);
                                    addGross(junkFood.getPrice());
                                    System.out.println("Pizza " + junkFood.getName() + " wurde deiner Bestellung hinzugefügt!");
                                    System.out.println();
                                    if (getGross() < getFreeDeliveryAmount()) { // check for final amount minimum order
                                        System.out.printf("Gratis Zustellung ab EUR " + getFreeDeliveryAmount() + "! Dazu brauchst du noch EUR %.2f%n", (getFreeDeliveryAmount() - getGross()));
                                    } else {
                                        System.out.printf("Bestellwert für Gratislieferung erreicht! Aktueller Saldo EUR: %.2f%n", getGross());
                                    }
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

                        System.out.println("Bitte den Namen des HotDogs eingeben: ");   //choose hotdog by name
                        String hotdogselected = scanner.nextLine();
                        for (JunkFood junkFood : hotDogList) {
                            if (junkFood instanceof HotDog) {

                                if (hotdogselected.equalsIgnoreCase(junkFood.getName())) {
                                    setOrder(order, junkFood);
                                    addGross(junkFood.getPrice());
                                    System.out.println("HotDog " + junkFood.getName() + " wurde deiner Bestellung hinzugefügt!");
                                    System.out.println();
                                    if (getGross() < getFreeDeliveryAmount()) { // check for final amount minimum order
                                        System.out.printf("Gratis Zustellung ab EUR " + getFreeDeliveryAmount() + "! Dazu brauchst du noch EUR %.2f%n", (getFreeDeliveryAmount() - getGross()));
                                    } else {
                                        System.out.printf("Bestellwert für Gratislieferung erreicht! Aktueller Saldo EUR: %.2f%n", getGross());
                                    }
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
            System.out.println();
            System.out.println("Weiteres fettiges JunkFood bestellen (j/n)");   // add further items?
            String further = scanner.nextLine();
            if (further.equalsIgnoreCase("n")) {
                furtherItemOrder = false;
            }
        }
        return order;
    }


    /**
     * display order that is above the free delivery amount
     * @param productsOrdered list of items ordered
     * @param time random delivery time
     */
    public void displayOrder(List<JunkFood> productsOrdered, int time) {
        System.out.println();
        System.out.println("************************************************");
        System.out.println("Zusammenfassung deiner Bestellung:");
        System.out.println();
        for (JunkFood junkFood : productsOrdered) {
            if (junkFood instanceof Burger) {
                System.out.println("Burger " + junkFood.getName() + ", EUR " + junkFood.getPrice());
            }
            if (junkFood instanceof Pizza) {
                System.out.println("Pizza " + junkFood.getName() + ", EUR " + junkFood.getPrice());
            }
            if (junkFood instanceof HotDog) {
                System.out.println("Hot Dog " + junkFood.getName() + ", EUR " + junkFood.getPrice());
            }
        }
        System.out.println();
        System.out.printf("Gesamtbetrag deiner Bestellung: EUR %.2f%n", getGross());
        System.out.println();
        System.out.println("Die Zustellung erfolgt in ca. " + time + " Minuten!");
        System.out.println("************************************************");
        System.out.println();
    }

    /**
     * display order that is below the free delivery amount
     * @param l reference to delivery class
     * @param productsOrdered  list of items ordered
     * @param time random delivery time
     */
    public void displayOrderBelowDelivery(Lieferung l, List<JunkFood> productsOrdered, int time) {
        Bestellung order = new Bestellung();
        double total = l.getTotal(order);
        System.out.println();
        System.out.println("************************************************");
        System.out.println("Zusammenfassung deiner Bestellung:");
        System.out.println();
        for (JunkFood junkFood : productsOrdered) {
            if (junkFood instanceof Burger) {
                System.out.println("Burger " + junkFood.getName() + ", EUR " + junkFood.getPrice());
            }
            if (junkFood instanceof Pizza) {
                System.out.println("Pizza " + junkFood.getName() + ", EUR " + junkFood.getPrice());
            }
            if (junkFood instanceof HotDog) {
                System.out.println("Hot Dog " + junkFood.getName() + ", EUR " + junkFood.getPrice());
            }
        }
        System.out.println();
        System.out.printf("Die Zustellgebühr von EUR %.2f wurde der Rechnung hinzugefügt!%n", l.getDeliveryCosts());
        System.out.println();
        System.out.printf("Rechnungsbetrag gesamt EUR: %.2f%n", total);
        System.out.println();
        System.out.println("Die Zustellung erfolgt in ca. " + time + " Minuten!");
        System.out.println("***************************************");
        System.out.println();
    }

    //G & S

    /**
     * get gross amount
     * @return gross value
     */
    public double getGross() {
        return gross;
    }

    /**
     * adds order gross values
     * @param gross new gross from order
     */
    public void addGross(double gross) {
        this.gross = getGross() + gross;
    }

    /**
     * get final free delevery amount
     * @return free delivery amount
     */
    public double getFreeDeliveryAmount() {
        return 14.90;
    }

    /**
     * setter method for ordered JF items
     * @param order list of JF items
     * @param product JF object
     */
    public void setOrder(List<JunkFood> order, JunkFood product) {
        order.add(product);
    }

}
