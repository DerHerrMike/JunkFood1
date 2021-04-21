package com.mps.app.shop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Rechnung {

    String item;
    String name;
    String price;
    double gross;
    double total;

    public Rechnung(String item, String name, String price, double gross, double total) {
        this.item = item;
        this.name = name;
        this.price = price;
        this.gross = gross;
        this.total = total;
    }

    public Rechnung() {
    }

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

    public String convert() {

        return this.getName() +
                "," +

                "," +
                this.getPrice() +
                "," +

                "," +

                "\n";
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
