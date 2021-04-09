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

    public void printAttributes(){

        System.out.println("Produkt: "+ getName());
        System.out.println("Preis EUR: "+ getPrice());
        System.out.println("Kalorien: "+getCalories());
    }

    @Override
    public String toString() {
        return "JunkFood{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", price=" + price +
                '}';
    }

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


    public abstract void createBurger();

    public abstract void createPizza();

    public abstract void createHotDog();
}
