package dev.collegues_api.repository;

import dev.collegues_api.controller.dto.ColleguePhotosDto;
import dev.collegues_api.model.Collegue;
import dev.collegues_api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollegueRepository extends JpaRepository<Collegue, String> {
    List<Collegue> findByNomIgnoreCase(String nom);

    @Query("select new dev.collegues_api.controller.dto.ColleguePhotosDto(c.photoUrl, c.matricule) from Collegue c")
    List<ColleguePhotosDto> findPhotosAndMatricules();

    Collegue findByMatriculeIgnoreCase(String matricule);
    Optional<Collegue> findByUtilisateur(Utilisateur utilisateur);
    Optional<Collegue> findByEmailIgnoreCase(String email);
}
