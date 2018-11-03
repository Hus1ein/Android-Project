package com.selma.constructions.model;

import java.io.Serializable;

public class Employee implements Serializable {

    private long id;
    private String name;//
    private String rank;//
    private String socialNumber;//JMB
    private String date;
    private String address;//
    private String email;
    private String phone;//
    private String imageUrl;
    private int workHours;

    public Employee() {}

    public Employee(long id, String name, String rank, String socialNumber, String date, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.socialNumber = socialNumber;
        this.date = date;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }
}
