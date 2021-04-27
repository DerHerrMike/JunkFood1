package com.mps.app.junkfood;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * The abstract class for burger, pizza, hotdog
 * @author MikeSchwingenschlögl
 *
 */
public abstract class JunkFood {

    private String name;
    private int calories;
    private double price;

    /**
     * JunkFood default constructor
     */
    public JunkFood() {
    }

    /**
     * Junkfood constructor with all fields as parameters
     * @param name  Junkfood name
     * @param calories Junkfood calories
     * @param price Junkfood price
     */
    public JunkFood(String name, int calories, double price) {
        this.name = name;
        this.calories = calories;
        this.price = price;
    }

    /**
     * abstract method to create various JunkFood objects
     * @param scanner
     * @throws Exception
     */
    protected abstract void create(Scanner scanner) throws Exception;

    /**
     * abstract method to write various Junkfood unto csv files
     * @param path path to burger, pizza, hd csv files
     * @throws IOException
     */
    protected abstract void writeFile (Path path) throws IOException;

    /**
     * abstract method to convert ArrayLists to a String
     * @return String of object details
     */
    protected abstract String convert();

    /**
     * abstract method to display various object details
     * @param products Lists of various junkfood loaded from file
     */
    protected abstract void displayJunkFood(List<JunkFood> products);


    /**
     * get JF name
     * @return JF name
     */
    // G & S
    public String getName() {
        return name;
    }

    /**
     * setter method for JF name
     * @param name the name of JF
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get JF calories
     * @return JF calories amount
     */
    public int getCalories() {
        return calories;
    }

    /**
     * setter method for JF calories
     * @param calories the number of calories of JF
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * get JF price
     * @return the price of JF
     */
    public double getPrice() {
        return price;
    }

    /**
     * setter method for JF price
     * @param price the price of JF
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
