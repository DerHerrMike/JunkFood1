package com.mps.app.junkfood;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public abstract class JunkFood {

    private String name;
    private int calories;
    private double price;

    public JunkFood() {
    }

    public JunkFood(String name, int calories, double price) {
        this.name = name;
        this.calories = calories;
        this.price = price;
    }

    protected abstract void create(Scanner scanner) throws Exception;

    protected abstract void writeFile (Path path) throws IOException;

    protected abstract String convert();

    protected abstract void displayJunkFood(List<JunkFood> products);


    // G & S
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
