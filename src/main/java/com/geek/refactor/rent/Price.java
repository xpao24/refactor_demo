package com.geek.refactor.rent;

import static com.geek.refactor.rent.Movie.CHILDRENS;
import static com.geek.refactor.rent.Movie.NEW_RELEASE;
import static com.geek.refactor.rent.Movie.REGULAR;

public abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}

class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }

}

class NewReleasePrice extends Price {
    @Override
    int getPriceCode() {
        return NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        if (daysRented > 1) {
            return 2;
        } else {
            return 1;
        }
    }
}

class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return REGULAR;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;

    }
}