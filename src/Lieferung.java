public class Lieferung {

    private int deliverytime;
    private final double deliveryCosts = 3.90;
    private boolean freeDelivery;


    public Lieferung() {
    }

    public Lieferung(int deliverytime, boolean freeDelivery) {
        this.deliverytime = deliverytime;
        this.freeDelivery = freeDelivery;
    }


    //G&S
    public int getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(int deliverytime) {
        this.deliverytime = deliverytime;
    }

    public double getDeliveryCosts() {
        return deliveryCosts;
    }

    public boolean isFreeDelivery() {
        return freeDelivery;
    }

    public void setFreeDelivery(boolean freeDelivery) {
        this.freeDelivery = freeDelivery;
    }

    @Override
    public String toString() {
        return "Lieferung{" +
                "deliverytime=" + deliverytime +
                ", deliveryCosts=" + deliveryCosts +
                ", freeDelivery=" + freeDelivery +
                '}';
    }
}
