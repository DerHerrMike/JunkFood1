import java.util.Scanner;

public class HotDog extends JunkFood{

private String sausageName;
private boolean xxl;

    public HotDog() {
    }

    public HotDog(String name, int calories, double price, String sausageName, boolean xxl) {
        super(name, calories, price);
        this.sausageName = sausageName;
        this.xxl = xxl;
    }


    private HotDog createHotDog(Scanner scanner){       //inputmismatch catch fehlt hier

        System.out.print("Name: ");
        setName(scanner.nextLine());
        System.out.print("Kalorien: ");
        setCalories(scanner.nextInt());
        System.out.print("Preis:");
        setPrice(scanner.nextDouble());
        System.out.print("Wurst wählen (1 für Frankfurter, 2 für Bratwurst): ");
        int sausage = scanner.nextInt();
        switch (sausage){
            case 1: setSausageName("Frankfurter");
                break;
            case 2: setSausageName("Bratwurst");
                break;
        }
        System.out.print("XXL (j/n): ");
        String cheeseSelected = scanner.nextLine();
        if (cheeseSelected.equalsIgnoreCase("j")){
            setXxl(true);
        }
        return new HotDog(getName(), getCalories(), getPrice(), getSausageName(),isXxl());
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
}
