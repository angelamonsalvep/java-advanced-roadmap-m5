package com.riwi.model;

public class CoffeeOrder {

    private String id;
    private String CustomerName;
    private String beverage;

    public CoffeeOrder(String id, String customerName, String beverage) {
        this.id = id;
        CustomerName = customerName;
        this.beverage = beverage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "id='" + id + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", beverage='" + beverage + '\'' +
                '}';
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getBeverage() {
        return beverage;
    }

    public void setBeverage(String beverage) {
        this.beverage = beverage;
    }
}
