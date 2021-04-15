package com.mps.app;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;
import com.mps.app.shop.Bestellung;
import com.mps.app.shop.Lieferung;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {


    public static void main(String[] args) throws IOException {

        Burger b = new Burger();
        Pizza p = new Pizza();
        HotDog h = new HotDog();
        Bestellung o = new Bestellung();
        Lieferung l = new Lieferung();
        Scanner scanner = new Scanner(System.in);
        Path path = Paths.get("C:\\Nerdwest\\JunkFood Excercise Fabien\\src\\com\\mps\\app\\output\\menu.csv");
        Path bpath = Paths.get("C:\\Nerdwest\\JunkFood Excercise Fabien\\src\\com\\mps\\app\\output\\burger.csv");
        Path ppath = Paths.get("C:\\Nerdwest\\JunkFood Excercise Fabien\\src\\com\\mps\\app\\output\\pizza.csv");
        Path hpath = Paths.get("C:\\Nerdwest\\JunkFood Excercise Fabien\\src\\com\\mps\\app\\output\\hotdog.csv");
        List<JunkFood> burgersFromFile;
        List<JunkFood> pizzasFromFile;
        List<JunkFood> hotdogsFromFile;
        List<JunkFood> productsOrdered= new ArrayList<>();
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        List<JunkFood> orderedItems = new ArrayList<>();
        boolean quit = false;
        printInstructions();
        while (!quit) {
            System.out.println("Bitte Auswahl treffen: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> printInstructions();
                case 1 -> b.create(scanner);
                case 2 -> p.create(scanner);
                case 3 -> h.create(scanner);
                case 4 -> {
                    System.out.println();
                    System.out.println("Die Speisekarte wurde geladen!");
                    b.displayJunkFood(b.readAllLinesFromFileInList(bpath));
                    p.displayJunkFood(p.readAllLinesFromFileinList(ppath));
                    h.displayJunkFood(h.readAllLinesFromFileInList(hpath));
                }
                case 5 -> {
                    burgersFromFile = b.readAllLinesFromFileInList(bpath);
                    pizzasFromFile= p.readAllLinesFromFileinList(ppath);
                    hotdogsFromFile = h.readAllLinesFromFileInList(hpath);
                    productsOrdered= o.menu(b,p,h,burgersFromFile,pizzasFromFile, hotdogsFromFile);

                    l.setDeliverytime(l.deliveryRand());
                    int time = l.getDeliverytime();
                    System.out.println("Die Zustellung erfolgt in ca. " + time + " Minuten!");
                    System.out.println(productsOrdered.toString());
                }
                case 9 -> quit = true;
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
