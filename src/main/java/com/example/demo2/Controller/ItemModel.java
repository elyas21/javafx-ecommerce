package com.example.demo2.Controller;

import javafx.beans.property.SimpleStringProperty;

public class ItemModel {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty brand;
    private SimpleStringProperty price;
    private  SimpleStringProperty stok;
    private  SimpleStringProperty sold;

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getStok() {
        return stok.get();
    }

    public SimpleStringProperty stokProperty() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok.set(stok);
    }

    public String getSold() {
        return sold.get();
    }

    public SimpleStringProperty soldProperty() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold.set(sold);
    }

    ItemModel(String id, String name, String brand, String price, String stok, String sold){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.brand = new SimpleStringProperty(brand);
        this.price = new SimpleStringProperty(price);
        this.stok = new SimpleStringProperty(stok);
        this.sold = new SimpleStringProperty(sold);

    }
}
