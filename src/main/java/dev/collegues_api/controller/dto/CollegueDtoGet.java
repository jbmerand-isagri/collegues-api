package dev.collegues_api.controller.dto;

import dev.collegues_api.model.Collegue;

import java.util.ArrayList;
import java.util.List;

public class CollegueDtoGet {

    private String matricule;
    private String nom;
    private String prenoms;
    private List<String> roles = new ArrayList<>();

    public CollegueDtoGet(Collegue collegue) {
        this.matricule = collegue.getMatricule();
        this.nom = collegue.getNom();
        this.prenoms = collegue.getPrenoms();
        this.roles = collegue.getUtilisateur().getRoles();
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
