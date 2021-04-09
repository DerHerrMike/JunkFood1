import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotDog extends JunkFood{

private String sausageName;
private boolean xxl;
private List<HotDog> hotdogCreated = new ArrayList<>();

    public HotDog() {
    }

    @Override
    protected Burger createBurger(Scanner scanner) {
        return null;
    }

    public HotDog(String name, int calories, double price, String sausageName, boolean xxl) {
        super(name, calories, price);
        this.sausageName = sausageName;
        this.xxl = xxl;
    }


    protected HotDog createHotDog(Scanner scanner){       //inputmismatch catch fehlt hier

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
        switch (sausage){
            case 1: setSausageName("Frankfurter");
                break;
            case 2: setSausageName("Bratwurst");
                break;
        }
        scanner.nextLine();
        System.out.println("XXL (j/n): ");
        String cheeseSelected = scanner.nextLine();
        if (cheeseSelected.equalsIgnoreCase("j")){
            setXxl(true);
        }
        HotDog h = new HotDog(getName(), getCalories(), getPrice(), getSausageName(),isXxl());
        hotdogCreated.add(h);
        return h;
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

    public List<HotDog> getHotdogCreated() {
        return hotdogCreated;
    }

    @Override
    public String toString() {
        return "HotDog{" +
                "sausageName='" + sausageName + '\'' +
                ", xxl=" + xxl +
                "} " + super.toString();
    }

    @Override
    public void createBurger() {

    }

    @Override
    public void createPizza() {

    }

    @Override
    public void createHotDog() {

    }

    @Override
    public List<Pizza> getPizzasCreated() {
        return null;
    }
}
