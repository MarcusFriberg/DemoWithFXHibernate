package com.example.demowithfxhibernate.model;

import javax.persistence.*;


/**
 * Created by Marcus Friberg on 2022-12-21.
 */
@Entity
@Table(name = "vehicle")
public class Car {
    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "price")
    private int price;
    @Column(name = "is_retired")
    private boolean retired;

    public Car() {
    }

    public Car(String brand, String model, int price, boolean retired) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.retired = retired;
    }

    public Car(int carId, String brand, String model, int price, boolean retired) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.retired = retired;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }
}
