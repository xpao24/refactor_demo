package com.geek.refactor.rent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 顾客
 */
public class Customer {
    private String name;
    private List<Rental> rentalList = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        this.rentalList.add(rental);
    }

    public String getName() {
        return name;
    }

    /**
     * 生成详单 影片租赁明细、费用、积分
     *
     * @return
     */
    public String statement() {
        Iterator<Rental> rentals = rentalList.iterator();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasNext()) {
            Rental each = rentals.next();
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        while (rentals.hasNext()) {
            Rental each = rentals.next();
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        while (rentals.hasNext()) {
            Rental each = rentals.next();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}
