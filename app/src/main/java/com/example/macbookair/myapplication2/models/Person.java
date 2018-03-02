package com.example.macbookair.myapplication2.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by macbookair on 1/20/18.
 */

@Entity
public class Person {

    private static final String category = "Lead";
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "Name")
    private String name;
    private int imageID;
    @ColumnInfo(name = "Social")
    private String socialNet;
    @ColumnInfo(name = "Country")
    private String country;
    @ColumnInfo(name = "Email")
    private String email;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public Person(String name, int imageID, String socialNet, String country, String email, byte[] image) {
        this.name = name;
        this.imageID = imageID;
        this.socialNet = socialNet;
        this.country = country;
        this.email = email;
        this.image = image;
    }

    public static String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getSocialNet() {
        return socialNet;
    }

    public void setSocialNet(String socialNet) {
        this.socialNet = socialNet;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
