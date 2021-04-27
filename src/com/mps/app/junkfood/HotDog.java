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

/**
 * CLass for hotdogs as JF objects.
 */
public class HotDog extends JunkFood {

    private String sausageName;
    private boolean xxl;
    private List<HotDog> hotdogCreated = new ArrayList<>();


    /**
     * Hotdog constructor with all fields as parameters
     * @param name name of hot dog
     * @param calories calories of hot dog
     * @param price price of hot dog
     * @param sausageName name of sausage in hot dog
     * @param xxl is it xxl?
     */
    public HotDog(String name, int calories, double price, String sausageName, boolean xxl) {
        super(name, calories, price);
        this.sausageName = sausageName;
        this.xxl = xxl;
    }

    /**
     * HotDog default constructor
     */
    public HotDog() {

    }
    /**
     * implements the abstract create method to create a hot dog object
     * adds burger to list
     * @param scanner
     * @throws IOException
     */
    @Override
    public void create(Scanner scanner) throws Exception {
        Path path = Paths.get("resources/hotdog.csv");
        scanner.nextLine();
        System.out.println("HotDog Name: ");
        String name = scanner.nextLine();
        setName(name);
        System.out.println("Kalorien: ");
        int calories = Integer.parseInt( scanner.nextLine());
        setCalories(calories);
        System.out.println("Preis:");
        double price = Double.parseDouble(scanner.nextLine());
        setPrice(price);
        System.out.println("Wurst wählen (1 für Frankfurter, 2 für Bratwurst): ");
        int sausage = Integer.parseInt( scanner.nextLine());
        switch (sausage) {
            case 1 -> setSausageName("Frankfurter");
            case 2 -> setSausageName("Bratwurst");
        }
        System.out.println("XXL (j/n): ");
        String cheeseSelected = scanner.nextLine();
        if (cheeseSelected.equalsIgnoreCase("j")) {
            setXxl(true);
        }
        HotDog h = new HotDog(getName(), getCalories(), getPrice(), getSausageName(), isXxl());
        h.writeFile(path);
        hotdogCreated.add(h);
    }

    /**
     * reads out file of saved hotdogs and adds it to list
     * @param path path to csv file with saved objects
     * @return list of hotdogs to work with
     * @throws IOException
     */
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
    /**
     * implements the write method burgers
     * @param path path to hotdogs csv file
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
     * implements the abstract method
     * @return String of attributes of hotdogs
     */
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

    /**
     * implements the display method for hotdogs
     * @param products Lists of hotdogs loaded from file
     */
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


    /**
     * get name of sausage for hotdog
     * @return name of sausage
     */
    public String getSausageName() {
        return sausageName;
    }

    /**
     * setter method for sausageName
     * @param sausageName the name of the selected sausage
     */
    public void setSausageName(String sausageName) {
        this.sausageName = sausageName;
    }

    /**
     * get info on xxl
     * @return xxl true or false
     */
    public boolean isXxl() {
        return xxl;
    }

    /**
     * setter method for xxl
     * @param xxl make it xxl
     */
    public void setXxl(boolean xxl) {
        this.xxl = xxl;
    }

}
