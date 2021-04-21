package com.mps.app.shop;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;

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

public class Rechnung {

    private String item;
    private String name;
    private String price;
    private double gross;


    public Rechnung(String item, String name, String price, double gross) {
        this.item = item;
        this.name = name;
        this.price = price;
        this.gross = gross;

    }

    public Rechnung() {
    }

    public void saveOrderToFile(List<JunkFood> productsOrdered) throws IOException {
        Path path = Paths.get("resources/turnover.csv");
        String item;
        String name;
        String price;
        double gross = 0;
        Bestellung o = new Bestellung();
        for (JunkFood junkFood : productsOrdered) {
            if (junkFood instanceof Burger) {
                item = "Burger";
                name = junkFood.getName();
                price = String.valueOf(junkFood.getPrice());
                setItem(item);
                setName(name);
                setPrice(price);
                gross = gross + junkFood.getPrice();

            }
            if (junkFood instanceof Pizza) {
                item = "Pizza";
                name = junkFood.getName();
                price = String.valueOf(junkFood.getPrice());
                setItem(item);
                setName(name);
                setPrice(price);
                gross = gross + junkFood.getPrice();
            }
            if (junkFood instanceof HotDog) {
                item = "HotDog";
                name = junkFood.getName();
                price = String.valueOf(junkFood.getPrice());
                setItem(item);
                setName(name);
                setPrice(price);
                gross = gross + junkFood.getPrice();
            }
            setGross(gross);
            Rechnung rechnung = new Rechnung(getItem(), getName(), getPrice(), getGross());
            rechnung.writeFile(path);
        }
    }


    private void writeFile(Path path) throws IOException {
        String object = convert();

        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        Files.write(
                path,
                object.getBytes(),
                StandardOpenOption.APPEND);
    }

    public String convert() {

        return this.getItem() +
                "," +
                this.getName() +
                "," +
                this.getPrice() +
                "," +
                this.getGross() + "\n";
    }


    public List<Double> loadTurnover(Path path) throws IOException {

        BufferedReader reader = null;
        List<Double> turnoverGrossExFile = new ArrayList<>();

        String[] ausgeleseneZeile;
        if (Files.size(path) < 1) {
            System.out.println("Kein Eintrag in Datei!");
            return null;
        } else {
            try {
                reader = new BufferedReader(new FileReader(String.valueOf(path)));
                String line = reader.readLine();
                while (line != null && !line.isEmpty()) {
                    ausgeleseneZeile = line.split(",");
                    //Gross
                    double grossFile = Double.parseDouble(ausgeleseneZeile[3]);
                    turnoverGrossExFile.add(grossFile);
                    line = reader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
        return turnoverGrossExFile;
    }

    public double calcLoadedTurnover(List<Double> turnover){
        double turnoverAmount = 0;
        for (Double aDouble : turnover) {
            turnoverAmount = turnoverAmount + aDouble;
        }
        return turnoverAmount;
    }

    public void displayTurnover(double turnoverAmount, Scanner scanner){

        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("Der bisherige Gesamtumsatz ohne Lieferkosten beträgt EUR: "+turnoverAmount);
        System.out.println("*******************************************************************");
        System.out.println();
        System.out.println("Zurück mit Enter!");
        scanner.nextLine();
    }

    //G&S
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

}
