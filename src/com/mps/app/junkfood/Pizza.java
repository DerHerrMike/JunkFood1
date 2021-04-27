package com.mps.app.junkfood;

import com.mps.app.util.FileUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for pizza as JF object
 */
public class Pizza extends JunkFood {

    private int durchmesser;
    private boolean isVeggy;
    private List<Pizza> pizzasCreated = new ArrayList<>();

    /**
     * Pizza default constructor
     */
    public Pizza() {
    }

    /**
     * Pizza constructor with all fields as parameters
     * @param name name of pizza
     * @param calories calories of pizza
     * @param price price of pizza
     * @param durchmesser diameter selected of pizza
     * @param isVeggy make a vegetarian pizza?
     */
    public Pizza(String name, int calories, double price, int durchmesser, boolean isVeggy) {
        super(name, calories, price);
        this.durchmesser = durchmesser;
        this.isVeggy = isVeggy;
    }

    /**
     * implements the abstract create method to create a pizza object
     * adds pizzas to list
     * @param scanner
     * @throws IOException
     */
    @Override
    public void create(Scanner scanner) throws IOException {
        Path path = Paths.get("resources/pizza.csv");
        scanner.nextLine();
        System.out.println("Pizza Name: ");
        String name = scanner.nextLine();
        setName(name);
        System.out.println("Kalorien: ");
        int calories =Integer.parseInt( scanner.nextLine());
        setCalories(calories);
        System.out.println("Preis:");
        double price = Double.parseDouble(scanner.nextLine());
        setPrice(price);
        System.out.println("Durchmesser (1 für 25cm, 2 für 30cm, 3 für 40cm): ");
        int diameter =Integer.parseInt( scanner.nextLine());
        switch (diameter) {
            case 1 -> setDurchmesser(25);
            case 2 -> setDurchmesser(30);
            case 3 -> setDurchmesser(40);
        }
        System.out.println("Extra Käse (j/n): ");
        String veggieSelected = scanner.nextLine();
        if (veggieSelected.equalsIgnoreCase("j")) {
            setVeggy(true);
        }
        Pizza p = new Pizza(getName(), getCalories(), getPrice(), getDurchmesser(), isVeggy());
        p.writeFile(path);
        pizzasCreated.add(p);
    }



     /**
     * reads out file of saved pizzas and adds it to list
     * @param path path to csv file with saved objects
     * @return list of pizzas to work with
     * @throws IOException
     */

    public List<JunkFood> readAllLinesFromFileinList(Path path) throws IOException {

        List<JunkFood> allPizzasFromMenuFile = new ArrayList<>();

        if (Files.size(path) < 1) {
            System.out.println("return null");
            return null;
        } else {

            try (BufferedReader reader = FileUtils.getReader(path)) {
                String line = FileUtils.skipBOM(reader.readLine());
                while (line != null) {
                    String[] ausgeleseneZeile = line.split(",");
                    //PName
                    String name = ausgeleseneZeile[0];
                    //P Calories
                    int calories = Integer.parseInt(ausgeleseneZeile[1]);
                    //P Price
                    double price = Double.parseDouble(ausgeleseneZeile[2]);
                    //B Durchmesser
                    int diameter = Integer.parseInt(ausgeleseneZeile[3]);
                    //P isVeggy
                    boolean veggy = Boolean.parseBoolean(ausgeleseneZeile[4]);
                    Pizza pizza = new Pizza(name, calories, price, diameter, veggy);
                    allPizzasFromMenuFile.add(pizza);
                    line = reader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allPizzasFromMenuFile;
    }

    /**
     * implements the write method for pizzas
     * @param path path to pizza csv file
     * @throws IOException
     */
    @Override
    public void writeFile(Path path) throws IOException {
        String object = convert();

        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        Files.write(
                path,
                object.getBytes(),
                StandardOpenOption.APPEND);
    }

    /**
     * converts veggy selection to String
     * @return String is veggy or not
     */
    public String convertVeggy() {
        boolean veggy = isVeggy();
        if (veggy) {
            return "ja";
        } else return "nein";
    }
    /**
     * implements the abstract method
     * @return String of attributes of pizza
     */
    @Override
    public String convert() {
        return this.getName() +
                "," +
                this.getCalories() +
                "," +
                this.getPrice() +
                "," +
                this.getDurchmesser() +
                "," +
                this.convertVeggy() +
                "\n";
    }

    /**
     * implements the display method for pizzas
     * @param products Lists of pizzas loaded from file
     */
    @Override
    public void displayJunkFood(List<JunkFood> products) {

        System.out.println();
        System.out.println("Verfügbare Pizzen: ");
        System.out.println();

        for (JunkFood item : products) {
            if (item instanceof Pizza) {
                System.out.print("Name: " + item.getName() + " || ");
                System.out.print("Kalorien: " + item.getCalories() + " || ");
                System.out.print("Preis €: " + item.getPrice() + " || ");
                System.out.print("Durchmesser: " + ((Pizza) item).getDurchmesser() + " || ");
                System.out.print("Vegetarisch: " + ((Pizza) item).convertVeggy() + " || ");
                System.out.println();
            } else {
                System.out.println("Wrong JunkFood Item in Pizza List!");

            }

            System.out.println();
        }
    }

    // G & S

    /**
     * get pizza diameter
     * @return diameter of pizza
     */
    public int getDurchmesser() {
        return durchmesser;
    }

    /**
     * setter method for diameter
     * @param durchmesser the selected diameter of pizza
     */
    public void setDurchmesser(int durchmesser) {
        this.durchmesser = durchmesser;
    }

    /**
     * get info on being vegetarian
     * @return veggy true or false
     */
    public boolean isVeggy() {
        return isVeggy;
    }

    /**
     * setter method for vegetarian pizza
     * @param veggy is veggy?
     */
    public void setVeggy(boolean veggy) {
        isVeggy = veggy;
    }
}
