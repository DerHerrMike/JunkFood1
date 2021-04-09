import java.util.List;

public class Bestellung {

    private List<JunkFood> order;
    private double gross;
    private final double minimumDeliveryAmount = 14.90;


    public Bestellung() {
    }

    public Bestellung(List<JunkFood> order, double gross) {
        this.order = order;
        this.gross = gross;
    }


    public List<JunkFood> menu(order){




    }

    //G & S


    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    public double getMinimumDeliveryAmount() {
        return minimumDeliveryAmount;
    }

    public List<JunkFood> getOrder() {
        return order;
    }

    public void setOrder(List<JunkFood> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "order=" + order +
                ", gross=" + gross +
                ", minimumDeliveryAmount=" + minimumDeliveryAmount +
                '}';
    }
}
