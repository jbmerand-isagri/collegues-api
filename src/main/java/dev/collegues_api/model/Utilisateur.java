package dev.collegues_api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UTILISATEUR")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String identifiant;
    private String motDePasse;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur(String identifiant, String motDePasse) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(String identifiant, String motDePasse, List<String> roles) {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
