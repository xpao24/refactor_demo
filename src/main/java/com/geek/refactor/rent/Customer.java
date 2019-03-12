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
            double thisAmount = 0;
            Rental each = rentals.next();

            thisAmount = amountFor(each);

            frequentRenterPoints++;

            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    private double amountFor(Rental aRental) {
        double thisAmount = 0;
        switch (aRental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (aRental.getDaysRented() > 2) {
                    thisAmount += (aRental.getDaysRented() - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += aRental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (aRental.getDaysRented() > 3) {
                    thisAmount += (aRental.getDaysRented() - 3) * 1.5;
                }
                break;
            default:
                thisAmount = 0;
        }
        return thisAmount;
    }
}
