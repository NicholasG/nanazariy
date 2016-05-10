package com.kursova.domain;

import java.io.Serializable;
import java.sql.Date;

public class Model implements Serializable {

    private int id;
    private String model;
    private String serial;
    private String color;
    private String type;
    private Date date;
    private String architecture;
    private int developerId;
    private int charId;

    public Model() {
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel( String model ) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial( String serial ) {
        this.serial = serial;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor( String color ) {
        this.color = color;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture( String architecture ) {
        this.architecture = architecture;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId( int developerId ) {
        this.developerId = developerId;
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId( int charId ) {
        this.charId = charId;
    }

}
