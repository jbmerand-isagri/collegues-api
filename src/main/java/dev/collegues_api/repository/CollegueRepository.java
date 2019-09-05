package dev.collegues_api.repository;

import dev.collegues_api.model.Collegue;
import dev.collegues_api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollegueRepository extends JpaRepository<Collegue, String> {
    List<Collegue> findByNomIgnoreCase(String nom);
    Collegue findByMatriculeIgnoreCase(String matricule);
    Optional<Collegue> findByUtilisateur(Utilisateur utilisateur);
}