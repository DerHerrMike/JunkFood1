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

public class Pizza extends JunkFood{

private int durchmesser;
private boolean isVeggy;
private List<Pizza> pizzasCreated = new ArrayList<>();

    public Pizza() {
    }

    public Pizza(String name, int calories, double price, int durchmesser, boolean isVeggy) {
        super(name, calories, price);
        this.durchmesser = durchmesser;
        this.isVeggy = isVeggy;
    }

    public void create(Scanner scanner) throws IOException {       //inputmismatch catch fehlt hier
        Path path = Paths.get("C:\\Nerdwest\\JunkFood Excercise Fabien\\src\\com\\mps\\app\\output\\pizza.csv");
        scanner.nextLine();
        System.out.println("Pizza Name: ");
        String name = scanner.nextLine();
        setName(name);
        System.out.println("Kalorien: ");
        int calories = scanner.nextInt();
        setCalories(calories);
        scanner.nextLine();
        System.out.println("Preis:");
        double price = scanner.nextDouble();
        setPrice(price);
        scanner.nextLine();
        System.out.println("Durchmesser (1 für 25cm, 2 für 30cm, 3 für 40cm): ");
        int diameter = scanner.nextInt();
        switch (diameter) {
            case 1 -> setDurchmesser(25);
            case 2 -> setDurchmesser(30);
            case 3 -> setDurchmesser(40);
        }
        scanner.nextLine();
        System.out.println("Extra Käse (j/n): ");
        String veggieSelected = scanner.nextLine();
        if (veggieSelected.equalsIgnoreCase("j")){
            setVeggy(true);
        }
        Pizza p = new Pizza(getName(), getCalories(), getPrice(), getDurchmesser(),isVeggy());
        p.writeFile(path);
        pizzasCreated.add(p);
    }


    public List<JunkFood> readAllLinesFromFileinList(Path path) throws IOException {

        BufferedReader reader = null;
        List<JunkFood> allPizzasFromMenuFile = new ArrayList<>();

        if (Files.size(path) < 1) {
            System.out.println("return null");
            return null;
        } else {

            try {
                reader = new BufferedReader(new FileReader(String.valueOf(path)));
                String line = reader.readLine();
                while (line != null) {
                    String[] ausgeleseneZeile = line.split(",");
                    //PName
                    String name  = ausgeleseneZeile[0];
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
            } finally {
                assert reader != null;
                reader.close();
            }
        }
        return allPizzasFromMenuFile;
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

    public String convertVeggy(){
        boolean veggy = isVeggy();
        if (veggy){
            return "ja";
        }else return "nein";
    }

    @Override
    public String convert() {
        return this.getName() +
                "," +
                this.getCalories() +
                "," +
                this.getPrice() +
                "," +
                this.getDurchmesser()+
                "," +
                this.convertVeggy()+
                "\n";
    }

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

    // G & S
    public int getDurchmesser() {
        return durchmesser;
    }

    public void setDurchmesser(int durchmesser) {
        this.durchmesser = durchmesser;
    }

    public boolean isVeggy() {
        return isVeggy;
    }

    public void setVeggy(boolean veggy) {
        isVeggy = veggy;
    }
}
