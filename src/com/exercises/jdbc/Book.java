package com.exercises.jdbc;

public class Book {
    private String bName;
    private double price;
    private int bTypeId;

    public Book() {
    }

    public Book(String bName, double price, int bTypeId) {
        this.bName = bName;
        this.price = price;
        this.bTypeId = bTypeId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getbTypeId() {
        return bTypeId;
    }

    public void setbTypeId(int bTypeId) {
        this.bTypeId = bTypeId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bName='" + bName + '\'' +
                ", price=" + price +
                ", bTypeId=" + bTypeId +
                '}';
    }
}
