package com.mps.app;

import com.mps.app.junkfood.*;
import com.mps.app.shop.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * main driver class
 * @author MikeSchwingenschlögl
 */
public class Driver {


    public static void main(String[] args) throws Exception {

        Burger b = new Burger();
        Pizza p = new Pizza();
        HotDog h = new HotDog();
        Bestellung o = new Bestellung();
        Lieferung l = new Lieferung();
        Rechnung r = new Rechnung();
        Scanner scanner = new Scanner(System.in);
        //relativer pfad unter project root/resources
        Path path = Paths.get("resources/turnover.csv");
        Path bpath = Paths.get("resources/burger.csv");
        Path ppath = Paths.get("resources/pizza.csv");
        Path hpath = Paths.get("resources/hotdog.csv");
        List<JunkFood> burgersFromFile;
        List<JunkFood> pizzasFromFile;
        List<JunkFood> hotdogsFromFile;
        List<JunkFood> productsOrdered;
        if (Files.notExists(path)) {    // check and create
            Files.createFile(path);
        }
        boolean quit = false;
        printWelcome();
        int identity = identifyUser(scanner);   // Admin or customer as user
        switch (identity) {
            case 1 -> {   //Customer
                while (!quit) {
                    int choice = displayChoiceCustomer(scanner); // selection menu for customer
                    switch (choice) {

                        case 1 -> loadMenu(b, p, h, bpath, ppath, hpath);   //display available burgers, pizzas, hotdogs
                        case 2 -> { //load saved objects from files into lists
                            burgersFromFile = b.readAllLinesFromFileInList(bpath);
                            pizzasFromFile = p.readAllLinesFromFileinList(ppath);
                            hotdogsFromFile = h.readAllLinesFromFileInList(hpath);
                            o.menu(b, p, h, burgersFromFile, pizzasFromFile, hotdogsFromFile);
                            productsOrdered = o.ordering(burgersFromFile, pizzasFromFile, hotdogsFromFile); // call ordering method
                            l.setDeliverytime(l.deliveryRand());    // call deliverytime
                            int time = l.getDeliverytime();
                            double total = l.getTotal(o);

                            if (total > 4) {    //check if an order has been placed and save it to file
                                if (total < o.getFreeDeliveryAmount()) {
                                    o.displayOrderBelowDelivery( l,productsOrdered,time);
                                } else {
                                    o.displayOrder(productsOrdered,  time);
                                }
                                r.saveOrderToFile(productsOrdered);
                            } else {
                                System.out.println("Kein Produkt bestellt. Weiter mit Enter!");
                                scanner.nextLine();
                            }
                        }
                        case 9 -> { //exit
                            System.out.println("Danke, dass du bei MegaMike vorbeigeschaut hast! Programm wird beendet.");
                            quit = true;
                        }
                        default -> throw new IllegalStateException("Ungültiger Wert: " + choice);
                    }
                }
            }
            case 2 -> {//admin from identifyUser
                while (!quit) {

                    int choice = displayChoiceAdmin(scanner);   // display admin menu
                    switch (choice) {
                        case 1 -> b.create(scanner);    // creates burger
                        case 2 -> p.create(scanner);    // creates pizza
                        case 3 -> h.create(scanner);    // creates hotdog
                        case 4 -> { // display available JF objects loaded
                            loadMenu(b, p, h, bpath, ppath, hpath);
                            System.out.println("Alle verfügbaren Produkte ausgegeben! Zurück mit Enter!");
                        }
                        case 5 -> r.displayTurnover(r.calcLoadedTurnover(r.loadTurnover(path)), scanner);   //calculates and displays the turnover so far
                        case 9 -> { //exit program
                            System.out.println("Programm wird beendet.");
                            quit = true;
                        }
                        default -> throw new IllegalStateException("Ungültiger Wert: " + choice);
                    }
                }
            }
        }
    }

    /**
     * displays welcome message
     */
    public static void printWelcome() {
        System.out.println();
        String output = """
                                                        
                    WILLKOMMEN BEI MEGA MIKE         
                   
                ** ekliges Junkfood - mega fett **
                   
                    """;
        System.out.println(output);
    }


    /**
     * selsct admin or customer, check password
     * @param scanner
     * @return selection of admin or customer
     */
    public static int identifyUser(Scanner scanner) {

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
                System.out.println("Bitte Admin Passwort eingeben oder zurück mit 'exit': ");
                String pw = scanner.nextLine();
                switch (pw) {   // checks admin password
                    case "Fett" -> {
                        return 2;
                    }
                    case "exit" -> identifyUser(scanner);
                    default -> {
                        System.out.println("Ungültiges Passwort! Zurück mit Enter!");
                        scanner.nextLine();
                    }
                }
            }
        }
    }

    /**
     * displays customer menu to select
     * @param scanner
     * @return customer selection
     */
    private static int displayChoiceCustomer(Scanner scanner) {

        int choice = 9;
        boolean correctSelection = false;
        while (!correctSelection) {

            System.out.println();
            System.out.println(" HAUPTMENÜ KUNDE: ");
            System.out.println();
            System.out.println(" 1 - Karte laden");
            System.out.println(" 2 - Bestellen");
            System.out.println(" 9 - Beenden");
            System.out.println("Bitte Auswahl im Programm-Menü treffen (1, 2, 9): ");
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

    /**
     * displays admin menu to select
     * @param scanner
     * @return admin selection
     */
    private static int displayChoiceAdmin(Scanner scanner) {

        int choice = 9;
        boolean correctSelection = false;
        while (!correctSelection) {

            System.out.println();
            System.out.println(" ADMIN HAUPTMENÜ: ");
            System.out.println();
            System.out.println(" 1 - Burger erstellen");
            System.out.println(" 2 - Pizza erstellen");
            System.out.println(" 3 - HotDog erstellen");
            System.out.println(" 4 - Karte laden");
            System.out.println(" 5 - Umsatz anzeigen");
            System.out.println(" 9 - Beenden");
            System.out.println("Bitte Auswahl im Programm-Menü treffen (1, 2, 3, 4, 9): ");
            String selection = scanner.nextLine();

            if (selection.equals("0") || selection.equals("1") || selection.equals("2") || selection.equals("3") || selection.equals("4") || selection.equals("5") || selection.equals("9")) {
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

    /**
     * loads all JF items from the csv files into lists
     * @param b burger class reference
     * @param p pizza class reference
     * @param h hotdog class reference
     * @param bpath path to burger csv
     * @param ppath path to pizza csv
     * @param hpath path to hotdog csv
     * @throws IOException
     */
    private static void loadMenu(Burger b, Pizza p, HotDog h, Path bpath, Path ppath, Path hpath) throws IOException {
        System.out.println();
        System.out.println("Die Speisekarte wurde geladen!");
        b.displayJunkFood(b.readAllLinesFromFileInList(bpath));
        p.displayJunkFood(p.readAllLinesFromFileinList(ppath));
        h.displayJunkFood(h.readAllLinesFromFileInList(hpath));
        System.out.println();
        System.out.println("Alle verfügbaren Produkte ausgegeben! Zurück mit Enter!");
    }
}
