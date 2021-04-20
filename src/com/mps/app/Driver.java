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
import java.util.List;
import java.util.Scanner;

public class Driver {

    private static final String password = "Fett";

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
        List<JunkFood> productsOrdered;
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
        boolean quit = false;
        printWelcome();
        int identity = identifyUser();
        switch (identity) {
            case 1 -> {   //Customer
                while (!quit) {
                    int choice = getChoiceCustomer(scanner);
                    switch (choice) {

                        case 0 -> printInstructionsCustomer();
                        case 1 -> {
                            loadMenu(b, p, h, bpath, ppath, hpath);
                            System.out.println("Alle verfügbaren Produkte ausgegeben! Zurück mit Enter!");
                            scanner.nextLine();
                        }
                        case 2 -> {
                            burgersFromFile = b.readAllLinesFromFileInList(bpath);
                            pizzasFromFile = p.readAllLinesFromFileinList(ppath);
                            hotdogsFromFile = h.readAllLinesFromFileInList(hpath);
                            o.menu(b, p, h, burgersFromFile, pizzasFromFile, hotdogsFromFile);
                            productsOrdered = o.ordering(burgersFromFile, pizzasFromFile, hotdogsFromFile);
                            l.setDeliverytime(l.deliveryRand());
                            int time = l.getDeliverytime();
                            double total = l.getTotal(o);
                            if (total < o.getMinimumDeliveryAmount()) {
                                Bestellung.displayOrderBelowDelivery(o, l, productsOrdered, time);
                            } else {
                                Bestellung.displayOrder(o, productsOrdered, time);
                            }
                        }
                        case 9 -> {
                            System.out.println("Danke, dass du bei MegaMike vorbeigeschaut hast! Programm wird beendet.");
                            quit = true;
                        }
                        default -> throw new IllegalStateException("Ungültiger Wert: " + choice);
                    }
                }
            }
            case 2 -> {//admin
                while (!quit) {

                    int choice = getChoiceAdmin(scanner);
                    switch (choice) {
                        case 0 -> printInstructionsAdmin();
                        case 1 -> b.create(scanner);
                        case 2 -> p.create(scanner);
                        case 3 -> h.create(scanner);
                        case 4 -> {
                            loadMenu(b, p, h, bpath, ppath, hpath);
                            System.out.println("Alle verfügbaren Produkte ausgegeben! Zurück mit Enter!");
                            scanner.nextLine();
                        }
                        case 9 -> {
                            System.out.println("Danke, dass du bei MegaMike vorbeigeschaut hast! Programm wird beendet.");
                            quit = true;
                        }
                        default -> throw new IllegalStateException("Ungültiger Wert: " + choice);
                    }
                }
            }
        }
    }

    private static int getChoiceCustomer(Scanner scanner) {

        int choice = 9;
        boolean correctSelection = false;
        while (!correctSelection) {
            scanner.nextLine();
            System.out.println();
            System.out.println(" HAUPTMENÜ KUNDE: ");
            System.out.println(" 0 - Auswahl anzeigen.");
            System.out.println(" 1 - Menü laden");
            System.out.println(" 2 - Bestellen");
            System.out.println(" 9 - Beenden");
            System.out.println("Bitte Auswahl im Programm-Menü treffen (0, 1, 2, 9): ");
            String selection = scanner.nextLine();
            if (selection.equals("0") || selection.equals("1") || selection.equals("2") || selection.equals("9")) {
                choice = Integer.parseInt(selection);
                correctSelection = true;
            } else {
                System.out.println("Fehlerhafte Eingabe, bitte nochmals versuchen. Abbruch mit X, weiter mit Enter!");
                String exit = scanner.nextLine();
                if (exit.equalsIgnoreCase("x")) {
                    System.out.println("Programm wird beendet!");
                    System.exit(0);
                }
            }
        }
        return choice;
    }

    private static int getChoiceAdmin(Scanner scanner) {

        int choice = 9;
        boolean correctSelection = false;
        while (!correctSelection) {
            scanner.nextLine();
            System.out.println();
            System.out.println(" ADMIN HAUPTMENÜ: ");
            System.out.println(" 0 - Auswahl anzeigen.");
            System.out.println(" 1 - Burger erstellen");
            System.out.println(" 2 - Pizza erstellen");
            System.out.println(" 3 - HotDog erstellen");
            System.out.println(" 4 - Menü laden");
            System.out.println(" 9 - Beenden");
            System.out.println("Bitte Auswahl im Programm-Menü treffen (0, 1, 2, 3, 4, 9): ");
            String selection = scanner.nextLine();
            if (selection.equals("0") || selection.equals("1") || selection.equals("2") || selection.equals("3") || selection.equals("4") || selection.equals("9")) {
                choice = Integer.parseInt(selection);
                correctSelection = true;
            } else {
                System.out.println("Fehlerhafte Eingabe, bitte nochmals versuchen. Abbruch mit X, weiter mit Enter!");
                String exit = scanner.nextLine();
                if (exit.equalsIgnoreCase("x")) {
                    System.out.println("Programm wird beendet!");
                    System.exit(0);
                }
            }
        }
        return choice;
    }

    public static void printWelcome() {
        System.out.println();
        String output = """
                                                        
                    WILLKOMMEN BEI MEGA MIKE         
                   
                ** ekliges Junkfood - mega fett **
                   
                    """;
        System.out.println(output);
    }


    public static int identifyUser() {

        Scanner scanner = new Scanner(System.in);
        int caseNum;

        while (true) {
            System.out.println();
            System.out.println("Wähle den Anwendungsfall. 1 für Kunde oder 2 für Administrator");
            String useCase = scanner.nextLine();

            while (!(useCase.equals("1") || useCase.equals("2"))) {
                System.out.println("Bitte ganzzahligen Wert 1 oder 2 wählen!");
                useCase = scanner.nextLine();
            }

            caseNum = Integer.parseInt(useCase);

            if (caseNum == 1) {
                return 1;
            } else {
                System.out.println("Bitte Admin Passwort eingeben: ");
                String pw = scanner.nextLine();
                switch (pw) {
                    case "Fett" -> {
                        return 2;
                    }
                    case "exit" -> {
                        identifyUser();
                    }
                    default -> {
                        System.out.println("Ungültiges Passwort! Zurück mit Enter!");
                        scanner.nextLine();
                    }
                }
            }
        }
    }

    private static void loadMenu(Burger b, Pizza p, HotDog h, Path bpath, Path ppath, Path hpath) throws IOException {
        System.out.println();
        System.out.println("Die Speisekarte wurde geladen!");
        b.displayJunkFood(b.readAllLinesFromFileInList(bpath));
        p.displayJunkFood(p.readAllLinesFromFileinList(ppath));
        h.displayJunkFood(h.readAllLinesFromFileInList(hpath));
    }


    public static void printInstructionsCustomer() {
        System.out.println();
        System.out.println(" HAUPTMENÜ KUNDE: ");
        System.out.println(" 0 - Auswahl anzeigen.");
        System.out.println(" 1 - Menü laden");
        System.out.println(" 2 - Bestellen");
        System.out.println(" 9 - Beenden");
    }

    public static void printInstructionsAdmin() {
        System.out.println();
        System.out.println(" ADMIN HAUPTMENÜ: ");
        System.out.println(" 0 - Auswahl anzeigen.");
        System.out.println(" 1 - Burger erstellen");
        System.out.println(" 2 - Pizza erstellen");
        System.out.println(" 3 - HotDog erstellen");
        System.out.println(" 4 - Menü laden");
        System.out.println(" 9 - Beenden");
    }

    public static String getPassword() {
        return password;
    }

}
