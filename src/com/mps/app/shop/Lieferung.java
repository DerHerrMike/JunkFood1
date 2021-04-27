package com.mps.app.shop;

import java.util.Random;

/**
 * @author MikeSchwingenschlögl
 * class for delivery data
 */
public class Lieferung {

    private int deliverytime;


    /**
     * default delivery constructor
     */
    public Lieferung() {
    }


    /**
     * get random delivery time
     * @return delivery time between 20 and 50 minutes
     */
    public int deliveryRand() {

        Random r = new Random();
        return (r.nextInt(30) + 20);
    }

    /**
     * get total value of order
     * @param bestellung reference to delivery class
     * @return total = gross + deliverycosts if applicable
     */
    public double getTotal(Bestellung bestellung) {
        double total;

        if (bestellung.getFreeDeliveryAmount() <= bestellung.getGross()) {
            return bestellung.getGross();
        } else {
            total = bestellung.getGross() + getDeliveryCosts();
        }
        return total;
    }

    /**get delivery time
     * @return delivery time
     */
    //G&S
    public int getDeliverytime() {
        return deliverytime;
    }

    /**
     * setter method for delivery time
     * @param deliverytime as int (20-50 mins)
     */
    public void setDeliverytime(int deliverytime) {
        this.deliverytime = deliverytime;
    }

    /**
     * @return final delivery costs
     */
    public double getDeliveryCosts() {
        return 3.90;
    }

}
