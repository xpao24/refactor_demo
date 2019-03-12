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
     * 生成详单
     *  影片租赁明细、费用、积分
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

            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2) {
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }

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

    public static void main(String[] args) {
        Movie movie = new Movie("东邪西毒", Movie.NEW_RELEASE);
        Customer customer = new Customer("路人甲");
        customer.addRental(new Rental(movie, 3));
        System.out.println(customer.statement());
    }
}
