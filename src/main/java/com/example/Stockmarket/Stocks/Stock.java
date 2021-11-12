package com.example.Stockmarket.Stocks;

public class Stock {
    private int id;
    private String name;
    private int price;
    private int dayChangePercentage;

    public Stock(int id, String name, int price, int dayChangePercentage) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.dayChangePercentage = dayChangePercentage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getDayChangePercentage() {
        return dayChangePercentage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDayChangePercentage(int dayChangePercentage) {
        this.dayChangePercentage = dayChangePercentage;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", dayChangePercentage=" + dayChangePercentage +
                '}';
    }
}
