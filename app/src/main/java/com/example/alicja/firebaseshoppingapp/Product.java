package com.example.alicja.firebaseshoppingapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Alicja on 05.01.2018.
 */

@IgnoreExtraProperties
public class Product implements Parcelable{

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    //Parcelling
    public Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readFloat();
        quantity = in.readInt();
        isBought = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeFloat(price);
        parcel.writeInt(quantity);
        parcel.writeByte((byte) (isBought ? 1 : 0));
    }




}
