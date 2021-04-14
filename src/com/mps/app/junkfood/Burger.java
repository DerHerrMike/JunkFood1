package com.mps.app.junkfood;

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
    public Burger create(Scanner scanner) {       //inputmismatch catch fehlt hier
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
        burgersCreated.add(b);
        return b;
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

    @Override
    public List<Pizza> getPizzasCreated() {
        return null;
    }
}
