package com.geek.refactor.rent;

/**
 * 电影
 */
public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private Price price;
    /**
     * 标题
     */
    private String title;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriceCode() {
        return this.getPrice().getPriceCode();
    }

    public void setPriceCode(int priceCode) {
        switch (priceCode) {
            case REGULAR:
                this.price = new RegularPrice();
                break;
            case NEW_RELEASE:
                this.price = new NewReleasePrice();
                break;
            case CHILDRENS:
                this.price = new ChildrensPrice();
                break;
            default:
                throw new IllegalArgumentException("Incorret Price Code");
        }
    }

    public Price getPrice() {
        return price;
    }

    public double getCharge(int daysRented) {
        return this.getPrice().getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return this.getPrice().getFrequentRenterPoints(daysRented);

    }
}
