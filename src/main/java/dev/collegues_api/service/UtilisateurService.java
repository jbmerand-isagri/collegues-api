package dev.collegues_api.service;

import dev.collegues_api.exception.UtilisateurNonTrouveException;
import dev.collegues_api.model.Utilisateur;
import dev.collegues_api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utilisateur recuperUtilisateurParIdentifiant(String identifiant) {
        return utilisateurRepository.findByIdentifiant(identifiant).orElseThrow(() -> new UtilisateurNonTrouveException(
                "ERREUR : utilisateur non trouvé à partir de cet identifiant."));
    }

    public void ajouterUnUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateurRepository.save(utilisateur);
    }

}
