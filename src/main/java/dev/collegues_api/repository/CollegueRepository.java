package dev.collegues_api.repository;

import dev.collegues_api.model.Collegue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegueRepository extends JpaRepository<Collegue, String> {
    List<Collegue> findByNomIgnoreCase(String nom);
    Collegue findByMatriculeIgnoreCase(String matricule);
}