package com.mps.app;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;
import com.mps.app.shop.Bestellung;
import com.mps.app.shop.Lieferung;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {


    public static void main(String[] args) {

        Burger b = new Burger();
        Pizza p = new Pizza();
        HotDog h = new HotDog();
        Bestellung o = new Bestellung();
        Lieferung l = new Lieferung();
        Scanner scanner = new Scanner(System.in);
        List<JunkFood> orderedItems = new ArrayList<>();
        boolean quit = false;
        printInstructions();
        while (!quit) {
            System.out.println("Bitte Auswahl treffen: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    b.create(scanner);
                    break;
                case 2:
                    p.create(scanner);
                    break;
                case 3:
                    h.create(scanner);
                    break;
                case 4:
                    System.out.println();


                    break;
                case 5:
                    orderedItems = o.menu(b.getBurgersCreated(), p.getPizzasCreated(), h.getHotdogCreated());
                    l.setDeliverytime(l.deliveryRand());
                    int time = l.getDeliverytime();
                    System.out.println("Die Zustellung erfolgt in ca. " + time + " Minuten!");
                    break;
                case 9:
                    quit = true;
            }
        }
    }

    public static void printInstructions() {
        System.out.println("\nBitte wählen: ");
        System.out.println("\t 0 - Auswahl anzeigen.");
        System.out.println("\t 1 - Burger erstellen");
        System.out.println("\t 2 - Pizza erstellen");
        System.out.println("\t 3 - HotDog erstellen\"");
        System.out.println("\t 4 - Menü laden");
        System.out.println("\t 5 - Bestellen");
        System.out.println("\t 9 - Beenden");
    }


}
