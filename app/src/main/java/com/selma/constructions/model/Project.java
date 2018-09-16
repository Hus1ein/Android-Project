package com.selma.constructions.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Project implements Serializable {
    @SerializedName("Id")
    private long id;
    @SerializedName("Naziv")
    private String name;
    @SerializedName("Lokacija")
    private String location;
    @SerializedName("DatumUgovora")
    private String contractDate;
    @SerializedName("PocetakProjekta")
    private String startProjectDate;
    @SerializedName("KrajProjekta")
    private String endProjectDate;
    @SerializedName("status")
    private boolean status;  // data type will be boolean
    @SerializedName("InvestitorId")
    private long investorId;
    @SerializedName("Investitor")
    private Investor investor;
    @SerializedName("KorisnikId")
    private long userId;
    @SerializedName("Korisnik")
    private User user;
    @SerializedName("Opis")
    private String description;

    public Project() {}

    public Project(long id, String name, String location, String endProjectDate, boolean status, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.endProjectDate = endProjectDate;
        this.status = status;
        this.description = description;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getStartProjectDate() {
        return startProjectDate;
    }

    public void setStartProjectDate(String startProjectDate) {
        this.startProjectDate = startProjectDate;
    }

    public String getEndProjectDate() {
        return endProjectDate;
    }

    public void setEndProjectDate(String endProjectDate) {
        this.endProjectDate = endProjectDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(long investorId) {
        this.investorId = investorId;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
