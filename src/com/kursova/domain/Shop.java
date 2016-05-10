package com.kursova.domain;

import java.io.Serializable;

public class Shop implements Serializable {

    private int id;
    private String name;
    private String address;
    private String phone;
    private String chief;
    private String site;
    private String schedule;

    public Shop() {
    }

    public Shop(
            String name,
            String address,
            String phone,
            String chief,
            String site,
            String schedule ) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.chief = chief;
        this.site = site;
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
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

    public String getChief() {
        return chief;
    }

    public void setChief( String chief ) {
        this.chief = chief;
    }

    public String getSite() {
        return site;
    }

    public void setSite( String site ) {
        this.site = site;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule( String schedule ) {
        this.schedule = schedule;
    }
}
