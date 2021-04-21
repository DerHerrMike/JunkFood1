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

public class HotDog extends JunkFood {

    private String sausageName;
    private boolean xxl;
    private List<HotDog> hotdogCreated = new ArrayList<>();


    public HotDog(String name, int calories, double price, String sausageName, boolean xxl) {
        super(name, calories, price);
        this.sausageName = sausageName;
        this.xxl = xxl;
    }

    public HotDog() {

    }

    @Override
    public void create(Scanner scanner) throws Exception {       //inputmismatch catch fehlt hier
        Path path = Paths.get("resources/hotdog.csv");
        scanner.nextLine();
        System.out.println("HotDog Name: ");
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
        System.out.println("Wurst wählen (1 für Frankfurter, 2 für Bratwurst): ");
        int sausage = scanner.nextInt();
        switch (sausage) {
            case 1 -> setSausageName("Frankfurter");
            case 2 -> setSausageName("Bratwurst");
        }
        scanner.nextLine();
        System.out.println("XXL (j/n): ");
        String cheeseSelected = scanner.nextLine();
        if (cheeseSelected.equalsIgnoreCase("j")) {
            setXxl(true);
        }
        HotDog h = new HotDog(getName(), getCalories(), getPrice(), getSausageName(), isXxl());
        h.writeFile(path);
        hotdogCreated.add(h);
    }

    public List<JunkFood> readAllLinesFromFileInList(Path path) throws IOException {
        List<JunkFood> allHotdogsFromMenuFile = new ArrayList<>();

        if (Files.size(path) < 1) {
            System.out.println("return null");
            return null;
        } else {

            try (BufferedReader bufferedReader = FileUtils.getReader(path)) {
                String line = FileUtils.skipBOM(bufferedReader.readLine());
                while (line != null) {
                    String[] ausgeleseneZeile = line.split(",");
                    //HotDogName
                    String name = ausgeleseneZeile[0];
                    //H Calories
                    int calories = Integer.parseInt(ausgeleseneZeile[1]);
                    //H Price
                    double price = Double.parseDouble(ausgeleseneZeile[2]);
                    //H Sausage
                    String sausage = ausgeleseneZeile[3];
                    //B isXXL
                    boolean xxl = Boolean.parseBoolean(ausgeleseneZeile[4]);
                    HotDog hotDog = new HotDog(name, calories, price, sausage, xxl);
                    allHotdogsFromMenuFile.add(hotDog);
                    line = bufferedReader.readLine();
                }


            }
        }
        return allHotdogsFromMenuFile;


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


    @Override
    public String convert() {
        return this.getName() +
                "," +
                this.getCalories() +
                "," +
                this.getPrice() +
                "," +
                this.getSausageName() +
                "," +
                this.convertXXL() +
                "\n";
    }

    public String convertXXL() {
        boolean xxl = isXxl();
        if (xxl) {
            return "Ja";
        } else return "Nein";
    }

    @Override
    public void displayJunkFood(List<JunkFood> products) {

        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("Verfügbare HotDogs: ");
        System.out.println();

        for (JunkFood item : products) {
            if (item instanceof HotDog) {
                System.out.print("Name: " + item.getName() + " || ");
                System.out.print("Kalorien: " + item.getCalories() + " || ");
                System.out.print("Preis €: " + item.getPrice() + " || ");
                System.out.print("Wurst: " + ((HotDog) item).getSausageName() + " || ");
                System.out.print("XXL: " + ((HotDog) item).convertXXL() + " || ");
                System.out.println();
            } else {
                System.out.println("Wrong JunkFood Item in HotDog List!");
            }
            System.out.println();
        }
    }

    //G & S
    public String getSausageName() {
        return sausageName;
    }

    public void setSausageName(String sausageName) {
        this.sausageName = sausageName;
    }

    public boolean isXxl() {
        return xxl;
    }

    public void setXxl(boolean xxl) {
        this.xxl = xxl;
    }

}
