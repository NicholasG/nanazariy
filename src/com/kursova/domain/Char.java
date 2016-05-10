package com.kursova.domain;

import java.io.Serializable;

public class Char implements Serializable {

    private int id;
    private String display;
    private String typeOfDisplay;
    private String processor;
    private String frequency;
    private String ram;
    private String typeOfRam;
    private String hardDisk;
    private String videoCard;
    private String webCam;
    private String os;

    public Char() {
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay( String display ) {
        this.display = display;
    }

    public String getTypeOfDisplay() {
        return typeOfDisplay;
    }

    public void setTypeOfDisplay( String typeOfDisplay ) {
        this.typeOfDisplay = typeOfDisplay;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor( String processor ) {
        this.processor = processor;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency( String frequency ) {
        this.frequency = frequency;
    }

    public String getRam() {
        return ram;
    }

    public void setRam( String ram ) {
        this.ram = ram;
    }

    public String getTypeOfRam() {
        return typeOfRam;
    }

    public void setTypeOfRam( String typeOfRam ) {
        this.typeOfRam = typeOfRam;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk( String hardDisk ) {
        this.hardDisk = hardDisk;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard( String videoCard ) {
        this.videoCard = videoCard;
    }

    public String getWebCam() {
        return webCam;
    }

    public void setWebCam( String webCam ) {
        this.webCam = webCam;
    }

    public String getOs() {
        return os;
    }

    public void setOs( String os ) {
        this.os = os;
    }
}
