package com.mps.app.shop;

import java.util.Random;

public class Lieferung {

    private int deliverytime;
    private final double deliveryCosts = 3.90;


    public Lieferung() {
    }


    public int deliveryRand(){

        Random r = new Random();
        return (r.nextInt(30) + 20);
    }

    public double getTotal(Bestellung bestellung) {
        double total;
        if (bestellung.getMinimumDeliveryAmount() <= bestellung.getGross()) {
            return bestellung.getGross();
        }else{
            total = bestellung.getGross()+getDeliveryCosts();
        }
        return total;
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

}
