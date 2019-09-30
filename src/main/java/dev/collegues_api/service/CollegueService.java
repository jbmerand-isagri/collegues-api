package dev.collegues_api.service;

import dev.collegues_api.controller.dto.ColleguePhotosDto;
import dev.collegues_api.exception.CollegueInvalideException;
import dev.collegues_api.exception.CollegueNonTrouveException;
import dev.collegues_api.model.Collegue;
import dev.collegues_api.model.Utilisateur;
import dev.collegues_api.repository.CollegueRepository;
import dev.collegues_api.utils.CollegueValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Classe gérant l'ensemble des services qui concernent les collègues.
 */
@Service
public class CollegueService {

    /**
     * LOGGER : Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CollegueService.class);

    private final CollegueRepository collegueRepository;
    private CollegueValidator collegueValidator;

    public long combien() {
        return this.collegueRepository.count();
    }

    /**
     * Constructor
     */
    @Autowired
    public CollegueService(CollegueValidator collegueValidator, CollegueRepository collegueRepository) {
        this.collegueValidator = collegueValidator;
        this.collegueRepository = collegueRepository;
    }

    /**
     * Méthode pour retourner une liste des collègues qui ont le nom précisé.
     *
     * @param nomRecherche : String nom du collègue
     * @return List<Collegue> Liste des collègues avec ce nom.
     */
    public List<Collegue> rechercherParNom(String nomRecherche) throws CollegueNonTrouveException {
        LOGGER.info("rechercheParNom() lancée");
        LOGGER.info("paramètre utilisé = " + nomRecherche);
        List<Collegue> listeCollegues = collegueRepository.findByNomIgnoreCase(nomRecherche);
        if (listeCollegues.size() < 1) {
            throw new CollegueNonTrouveException("ERREUR : Ce nom correspond à aucun collègue");
        }

        return listeCollegues;
    }

    /**
     * Méthode pour retourner le collègue correspondant au matricule entré.
     *
     * @param matriculeRecherche : String matricule du collègue
     * @return : Collegue collègue dont le matricule correspond
     * @throws CollegueNonTrouveException : Exception si jamais aucun collègue ne
     *                                    correspond au matricule.
     */
    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
        LOGGER.info("rechercheParMatricule() lancée");
        LOGGER.info("paramètre utilisé = " + matriculeRecherche);
        Collegue collegue = collegueRepository.findByMatriculeIgnoreCase(matriculeRecherche);
        if (collegue == null) {
            throw new CollegueNonTrouveException("ERREUR : Ce matricule correspond à aucun collègue.");
        }
        return collegue;
    }

    public Boolean rechercherEmailExiste(String emailRecherche) {
        LOGGER.info("rechercherEmailExiste() lancée");
        LOGGER.info("paramètre utilisé = " + emailRecherche);
        return collegueRepository.findByEmailIgnoreCase(emailRecherche).isPresent();
    }

    public Collegue rechercherCollegueParUtilisateur(Utilisateur utilisateur) {
        return collegueRepository.findByUtilisateur(utilisateur).orElseThrow(() -> new CollegueNonTrouveException(
                "ERREUR : aucun collegue trouvé pour cet utilisateur."));
    }

    /**
     * Méthode pour ajouter un collègue.
     *
     * @param collegueAAjouter : Collegue le collègue à ajouter
     * @return : Collegue le collègue ajouté
     * @throws CollegueInvalideException : Exception dans le cas où des données du
     *                                   collègue à ajouter ne répondent pas au
     *                                   format imposé.
     */
    public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {

        LOGGER.info("ajouterUnCollegue() lancée");
        LOGGER.info("collegue à ajouter avant validation = " + collegueAAjouter);

        if (collegueValidator.isCollegueInfosCorrectes(collegueAAjouter)) {
            LOGGER.info("collegue à ajouter après validation = " + collegueAAjouter);
            if(collegueAAjouter.getMatricule() == null) {
                collegueAAjouter.setMatricule(UUID.randomUUID().toString());
            }
            collegueRepository.save(collegueAAjouter);
        } else {
            throw new CollegueInvalideException("ERREUR : Au moins une des données du collègue hors format");
        }

        return collegueAAjouter;
    }

    /**
     * Méthode pour modifier l'email d'un collègue.
     *
     * @param matricule : String le matricule du collègue
     * @param email     : String le nouvel email du collègue
     * @return : Collegue le collègue modifié
     */
    public Collegue modifierEmail(String matricule, String email) throws CollegueInvalideException {

        Collegue collegue = rechercherParMatricule(matricule);

        if (collegueValidator.isFormatEmailCorrect(email)) {
            collegue.setEmail(email);
            collegueRepository.save(collegue);
        } else {
            throw new CollegueInvalideException("ERREUR : Email incorrect (il ne respecte pas le format).");
        }

        return collegue;
    }

    /**
     * Méthode pour modifier l'url de la photo d'un collègue.
     *
     * @param matricule : String matricule du collègue
     * @param photoUrl  : String le nouvel url de la photo du collègue
     * @return : Collegue le collègue amodifié
     */
    public Collegue modifierPhotoUrl(String matricule, String photoUrl) throws CollegueInvalideException {

        Collegue collegue = rechercherParMatricule(matricule);

        if (collegueValidator.isFormatUrlPhotoCorrect(photoUrl)) {
            collegue.setPhotoUrl(photoUrl);
            collegueRepository.save(collegue);
        } else {
            throw new CollegueInvalideException("ERREUR : Url de la photo incorrecte (elle ne commence pas par http).");
        }

        return collegue;
    }

    public List<ColleguePhotosDto> recupererPhotoUrlCollegues() {
        return collegueRepository.findPhotosAndMatricules();
    }
}
