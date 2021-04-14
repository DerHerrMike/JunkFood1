package com.mps.app.junkfood;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Burger extends JunkFood {

    private int size;
    private boolean cheese;
    private List<Burger> burgersCreated = new ArrayList<>();


    public Burger() {
    }

    public Burger(String name, int calories, double price, int size, boolean cheese) {
        super(name, calories, price);
        this.size = size;
        this.cheese = cheese;
    }

    @Override
    public Burger create(Scanner scanner) throws IOException {       //inputmismatch catch fehlt hier
        Path path = Paths.get("C:\\Nerdwest\\JunkFood Excercise Fabien\\src\\com\\mps\\app\\output\\burger.csv");
        scanner.nextLine();
        System.out.println();
        System.out.println("BURGER ERSTELLEN");
        System.out.println();
        System.out.println("Burger Name: ");
        String name = scanner.nextLine();
        setName(name);
        System.out.println("Kalorien: ");
        int calories = scanner.nextInt();
        scanner.nextLine();
        setCalories(calories);
        System.out.println("Preis:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        setPrice(price);
        System.out.println("Größe (1 für Standard, 2 für Super, 3 für Mega): ");
        int sizeSelection = scanner.nextInt();
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
        return b;
    }

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
                this.isCheese() +
                "\n";
    }

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
                System.out.print("Käse: " + ((Burger) item).isCheese() + " || ");
                System.out.println();

            } else {
                System.out.println("Wrong JunkFood Item in Burger List!");
            }

            System.out.println();
        }
    }

    public List<JunkFood> readAllLinesFromFileInList(Path path) throws IOException {

        BufferedReader reader = null;
        List<JunkFood> allBurgersFromMenuFile = new ArrayList<>();

        if (Files.size(path) < 1) {
            System.out.println("return null");
            return null;
        } else {

            try {
                reader = new BufferedReader(new FileReader(String.valueOf(path)));
                String line = reader.readLine();
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
                    line = reader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                assert reader != null;
                reader.close();
            }
        }
        return allBurgersFromMenuFile;
    }

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


    public void displaySize(int size) {
        switch (size) {
            case 1:
                System.out.println("Standard");
                break;
            case 2:
                System.out.println("Super");
                break;
            case 3:
                System.out.println("Mega");
                break;
        }
    }

    // G & S


    public List<Burger> getBurgersCreated() {
        return burgersCreated;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isCheese() {
        return cheese;
    }

    public void setCheese(boolean cheese) {
        this.cheese = cheese;
    }

    @Override
    public String toString() {
        return ".Burger{" +
                "size=" + size +
                ", cheese=" + cheese +
                "} " + super.toString();
    }

    @Override
    public void printAttributes() {
        super.printAttributes();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getCalories() {
        return super.getCalories();
    }

    @Override
    public void setCalories(int calories) {
        super.setCalories(calories);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(double price) {
        super.setPrice(price);
    }
}

