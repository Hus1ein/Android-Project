package com.selma.constructions.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("id")
    private long id;
    @SerializedName("isActive")
    private boolean isActive;
    @SerializedName("KorisnickoIme")
    private String userName;
    @SerializedName("Lozinka")
    private String password;
    @SerializedName("Email")
    private String email;
    @SerializedName("Ime")
    private String firstName;
    @SerializedName("Prezime")
    private String lastName;
    @SerializedName("DatumRodjenja")
    private String birthDate;
    @SerializedName("StrucnoZanimanje")
    private String profession;
    @SerializedName("Adresa")
    private String address;
    @SerializedName("KontaktTelefon")
    private String phone;
    @SerializedName("ProjektnaDokumentacijaId")
    private long projectDocumentationId;
    @SerializedName("ProjektnaDokumentacija ")
    private ProjectDocumentation projectDocumentation;
    @SerializedName("TehnickiOpisId")
    private TechnicalDescription technicalDescription;

    public long getProjectDocumentationId() {
        return projectDocumentationId;
    }

    public void setProjectDocumentationId(long projectDocumentationId) {
        this.projectDocumentationId = projectDocumentationId;
    }

    public ProjectDocumentation getProjectDocumentation() {
        return projectDocumentation;
    }

    public void setProjectDocumentation(ProjectDocumentation projectDocumentation) {
        this.projectDocumentation = projectDocumentation;
    }

    public TechnicalDescription getTechnicalDescription() {
        return technicalDescription;
    }

    public void setTechnicalDescription(TechnicalDescription technicalDescription) {
        this.technicalDescription = technicalDescription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
