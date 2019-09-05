package dev.collegues_api.controller.dto;

import dev.collegues_api.model.Collegue;

public class CollegueDto {

    private String matricule;
    private String nom;
    private String prenoms;
    private String photoUrl;
    private String email;

    public CollegueDto(Collegue collegue) {
        this.matricule = collegue.getMatricule();
        this.nom = collegue.getNom();
        this.prenoms = collegue.getPrenoms();
        this.photoUrl = collegue.getPhotoUrl();
        this.email = collegue.getEmail();
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
