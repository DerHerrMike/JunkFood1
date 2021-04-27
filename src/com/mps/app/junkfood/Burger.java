package com.mps.app.junkfood;

import com.mps.app.util.FileUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Class for Burger as JF object
 * @author MikeSchwingenschlögl
 */
public class Burger extends JunkFood {

    private int size;
    private boolean cheese;
    private List<Burger> burgersCreated = new ArrayList<>();


    /**
     * Burger default constructor
     */
    public Burger() {
    }

    /**
     * Burger constructor with all fields as parameters
     * @param name name of burgers
     * @param calories calories of burgers
     * @param price price of burgers
     * @param size additional size of burgers
     * @param cheese additional cheese in burgers
     */
    public Burger(String name, int calories, double price, int size, boolean cheese) {
        super(name, calories, price);
        this.size = size;
        this.cheese = cheese;
    }

    /**
     * implements the abstract create method to create a burger object
     * adds burger to list
     * @param scanner
     * @throws IOException
     */
    @Override
    public void create(Scanner scanner) throws IOException {
        Path path = Paths.get("resources/burger.csv");
        scanner.nextLine();
        System.out.println();
        System.out.println("BURGER ERSTELLEN");
        System.out.println();
        System.out.println("Burger Name: ");
        String name = scanner.nextLine();
        setName(name);
        System.out.println("Kalorien: ");
        int calories = Integer.parseInt( scanner.nextLine());
        setCalories(calories);
        System.out.println("Preis:");
        double price = Double.parseDouble(scanner.nextLine());
        scanner.nextLine();
        setPrice(price);
        System.out.println("Größe (1 für Standard, 2 für Super, 3 für Mega): ");
        int sizeSelection = Integer.parseInt( scanner.nextLine());
        scanner.nextLine();
        setSize(sizeSelection);
        System.out.println("Extra Käse (j/n): ");
        String cheeseSelected = scanner.nextLine();
        if (cheeseSelected.equalsIgnoreCase("j")) {
            setCheese(true);
        }
        Burger b = new Burger(getName(), getCalories(), getPrice(), getSize(), isCheese());
        b.writeFile(path);
        burgersCreated.add(b);
    }

    /**
     * reads out file of saved burgers and adds it to list
     * @param path path to csv file with saved objects
     * @return list of burgers to work with
     * @throws IOException
     */
    public List<JunkFood> readAllLinesFromFileInList(Path path) throws IOException {


        List<JunkFood> allBurgersFromMenuFile = new ArrayList<>();

        if (Files.size(path) < 1) {
            System.out.println("return null");
            return Collections.emptyList();
        } else {

            try (BufferedReader bufferedReader = FileUtils.getReader(path)) {
                String line = FileUtils.skipBOM(bufferedReader.readLine());
                while (line != null) {
                    String[] ausgeleseneZeile = line.split(",");
                    //BurgerName
                    String name = ausgeleseneZeile[0];
                    //B Calories
                    int calories = Integer.parseInt(ausgeleseneZeile[1]);
                    //B Price
                    double price = Double.parseDouble(ausgeleseneZeile[2]);
                    //B Size
                    int size = Integer.parseInt(ausgeleseneZeile[3]);
                    //B isCheese
                    boolean cheese = Boolean.parseBoolean(ausgeleseneZeile[4]);
                    Burger burger = new Burger(name, calories, price, size, cheese);
                    allBurgersFromMenuFile.add(burger);
                    line = bufferedReader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return allBurgersFromMenuFile;
    }

    /**
     * implements the abstract method
     * @return String of attributes of burgers
     */
    @Override
    public String convert() {

        return this.getName() +
                "," +
                this.getCalories() +
                "," +
                this.getPrice() +
                "," +
                this.getSize() +
                "," +
                this.convertCheese() +
                "\n";
    }

    /**
     * implements the display method for burgers
     * @param products Lists of burgers loaded from file
     */
    @Override
    public void displayJunkFood(List<JunkFood> products) {

        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("Verfügbare Burger: ");
        System.out.println();

        for (JunkFood item : products) {
            if (item instanceof Burger) {
                System.out.print("Name: " + item.getName() + " || ");
                System.out.print("Kalorien: " + item.getCalories() + " || ");
                System.out.print("Preis €: " + item.getPrice() + " || ");
                System.out.print("Größe: " + ((Burger) item).getSize() + " || ");
                System.out.print("Käse: " + ((Burger) item).convertCheese() + " || ");
                System.out.println();

            } else {
                System.out.println("Wrong JunkFood Item in Burger List!");
            }

            System.out.println();
        }
    }

    /**
     * implements the write method burgers
     * @param path path to burger csv file
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
     * converts cheese selection to String
     * @return String with cheese or without
     */
    public String convertCheese() {
        boolean cheese = isCheese();
        if (cheese) {
            return "mit Käse";
        } else return "ohne Käse";
    }

    //G&S

    /**
     * get burger size
     * @return size of burger
     */
    public int getSize() {
        return size;
    }

    /**
     * setter method for burger size
     * @param size the selected size of burger
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * get info on cheese
     * @return cheese true or false
     */
    public boolean isCheese() {
        return cheese;
    }

    /**
     * setter method for added cheese
     * @param cheese added cheese
     */
    public void setCheese(boolean cheese) {
        this.cheese = cheese;
    }
}

