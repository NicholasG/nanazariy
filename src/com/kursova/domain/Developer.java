package com.kursova.domain;

import java.io.Serializable;

public class Developer implements Serializable {

    private int id;
    private String brand;
    private String office;
    private String country;
    private String city;
    private String address;
    private String phone;
    private String site;
    private String founder;
    private String rating;

    public Developer() {
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand( String brand ) {
        this.brand = brand;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder( String founder ) {
        this.founder = founder;
    }

    public String getSite() {
        return site;
    }

    public void setSite( String site ) {
        this.site = site;
    }

    public String getRating() {
        return rating;
    }

    public void setRating( String rating ) {
        this.rating = rating;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice( String office ) {
        this.office = office;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

}
