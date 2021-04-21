package com.mps.app.shop;

import com.mps.app.junkfood.Burger;
import com.mps.app.junkfood.HotDog;
import com.mps.app.junkfood.JunkFood;
import com.mps.app.junkfood.Pizza;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> saveOrderToFile(List<JunkFood> productsOrdered) throws IOException {
        Path path = Paths.get("resources/turnover.csv");
        String item;
        String name;
        String price;
        List<String> savedOrder = new ArrayList<>();
        for (JunkFood junkFood : productsOrdered) {
            if (junkFood instanceof Burger) {
                item = "Burger";
                name = junkFood.getName();
                price = String.valueOf(junkFood.getPrice());
                savedOrder.add(item);
                savedOrder.add(name);
                savedOrder.add(price);
                setItem(item);
                setName(name);
                setPrice(price);

            }
            if (junkFood instanceof Pizza) {
                item = "Pizza";
                name = junkFood.getName();
                price = String.valueOf(junkFood.getPrice());
                savedOrder.add(item);
                savedOrder.add(name);
                savedOrder.add(price);
                setItem(item);
                setName(name);
                setPrice(price);
            }
            if (junkFood instanceof HotDog) {
                item = "HotDog";
                name = junkFood.getName();
                price = String.valueOf(junkFood.getPrice());
                savedOrder.add(item);
                savedOrder.add(name);
                savedOrder.add(price);
                setItem(item);
                setName(name);
                setPrice(price);
            }
            double gross = 0;
            for (JunkFood junk : productsOrdered) {
                gross = gross + getGross();
            }
            setGross(gross);
            Rechnung rechnung = new Rechnung(getItem(), getName(), getPrice(), getGross());
            rechnung.writeFile(path);
        }
        for (String s : savedOrder) {
            System.out.println(s);
        }
        return savedOrder;
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
