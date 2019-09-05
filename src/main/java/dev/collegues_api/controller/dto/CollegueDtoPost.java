package dev.collegues_api.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CollegueDtoPost {

    @NotBlank(message = "nom manquant")
    private String nom;
    @NotBlank(message = "prenoms non renseignés")
    private String prenoms;
    @NotBlank(message = "email manquant")
    private String email;
    @NotNull(message = "date de naissance manquante")
    private LocalDate dateDeNaissance;
    @NotBlank(message = "url de la photo manquante")
    private String photoUrl;
    @NotBlank(message = "identifiant manquant")
    @Size(min = 6, max = 32, message = "l'identifiant doit comportant de 6 à 32 caractères")
    private String identifiant;
    @NotBlank(message = "mot de passe manquant")
    @Size(min = 6, max = 32, message = "le mot de passe doit comporter de 6 à 32 caractères")
    private String motDePasse;
    @NotBlank(message = "role manquant")
    private String role;

    public CollegueDtoPost(String nom, String prenoms, String email, LocalDate dateDeNaissance,
                           String photoUrl, String identifiant, String motDePasse, String role) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.photoUrl = photoUrl;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
