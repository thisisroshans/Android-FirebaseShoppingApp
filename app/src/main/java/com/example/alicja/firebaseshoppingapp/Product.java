package com.example.alicja.firebaseshoppingapp;

/**
 * Created by Alicja on 05.01.2018.
 */

public class Product {

    private String id;
    private String name;
    private float price;
    private int quantity;
    private boolean isBought;

    public Product() {
    }

    public Product(String id, String name, float price, int quantity, boolean isBought) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isBought = isBought;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getIsBought() {
        return isBought;
    }

    public void setIsBought(boolean bought) {
        isBought = bought;
    }
}
