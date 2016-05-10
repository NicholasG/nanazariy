package com.kursova.domain;

import java.io.Serializable;

public class Good implements Serializable {

    private int id;
    private String name;
    private String type;
    private String manufacturer;
    private String article;
    private float price;
    private String scale;
    private int amount;
    private String color;
    private String specifications;

    public Good() {
    }

    public Good(
            String name,
            String type,
            String manufacturer,
            String article,
            float price,
            String scale,
            int amount,
            String color,
            String specifications ) {
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.article = article;
        this.price = price;
        this.scale = scale;
        this.amount = amount;
        this.color = color;
        this.specifications = specifications;
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

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer( String manufacturer ) {
        this.manufacturer = manufacturer;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle( String article ) {
        this.article = article;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice( float price ) {
        this.price = price;
    }

    public String getScale() {
        return scale;
    }

    public void setScale( String scale ) {
        this.scale = scale;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount( int amount ) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor( String color ) {
        this.color = color;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications( String specifications ) {
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", article='" + article + '\'' +
                ", price=" + price +
                ", scale='" + scale + '\'' +
                ", amount=" + amount +
                ", color='" + color + '\'' +
                ", specifications='" + specifications + '\'' +
                '}';
    }
}
