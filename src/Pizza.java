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

    protected Pizza createPizza(Scanner scanner){       //inputmismatch catch fehlt hier

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
        switch (diameter){
            case 1: setDurchmesser(25);
            break;
            case 2: setDurchmesser(30);
            break;
            case 3: setDurchmesser(40);
            break;
        }
        scanner.nextLine();
        System.out.println("Extra Käse (j/n): ");
        String veggieSelected = scanner.nextLine();
        if (veggieSelected.equalsIgnoreCase("j")){
            setVeggy(true);
        }
        Pizza p = new Pizza(getName(), getCalories(), getPrice(), getDurchmesser(),isVeggy());
        pizzasCreated.add(p);
        return p;
    }


    @Override
    public void printAttributes() {
        super.printAttributes();
    }

    @Override
    protected Burger createBurger(Scanner scanner) {
        return null;
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
        return pizzasCreated;
    }


    @Override
    public String toString() {
        return "Pizza{" +
                "durchmesser=" + durchmesser +
                ", isVeggy=" + isVeggy +
                "} " + super.toString();
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
