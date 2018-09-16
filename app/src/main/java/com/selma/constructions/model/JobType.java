package com.selma.constructions.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobType {
    @SerializedName("listTipPosla")
    private List<JobTypeRow> jobTypeRowList;
    @SerializedName("GradilisteId")
    private int constructionId;

    public List<JobTypeRow> getJobTypeRowList() {
        return jobTypeRowList;
    }

    public void setJobTypeRowList(List<JobTypeRow> jobTypeRowList) {
        this.jobTypeRowList = jobTypeRowList;
    }

    public int getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(int constructionId) {
        this.constructionId = constructionId;
    }
}
