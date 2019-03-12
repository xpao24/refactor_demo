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
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Iterator<Rental> rentals = rentalList.iterator();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasNext()) {

            Rental each = rentals.next();

            frequentRenterPoints += each.getFrequentRenterPoints();
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
            totalAmount += each.getCharge();
        }
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

}
