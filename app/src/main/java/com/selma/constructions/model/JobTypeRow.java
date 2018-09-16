package com.selma.constructions.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JobTypeRow implements Serializable{
    @SerializedName("TipPoslaId")
    private long id;
    @SerializedName("TipPoslaNaziv")
    private String name;
    @SerializedName("count")
    private int count;

    public JobTypeRow() {}

    public JobTypeRow(long id, String name, int count){
        this.id = id;
        this.name = name;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
