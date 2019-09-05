package dev.collegues_api.controller;

import dev.collegues_api.controller.dto.CollegueDtoGet;
import dev.collegues_api.controller.dto.CollegueDtoPost;
import dev.collegues_api.exception.CollegueInvalideException;
import dev.collegues_api.exception.CollegueNonTrouveException;
import dev.collegues_api.model.Collegue;
import dev.collegues_api.model.Utilisateur;
import dev.collegues_api.service.CollegueService;
import dev.collegues_api.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Classe représentant le controleur qui concerne les collègues.
 */
@Controller
public class CollegueController {

    private final Logger LOGGER = LoggerFactory.getLogger(CollegueController.class);

    /**
     * collegueService : CollegueService
     */
    private CollegueService collegueService;

    private UtilisateurService utilisateurService;

    /**
     * Constructor
     *
     * @param collegueService
     */
    @Autowired
    public CollegueController(CollegueService collegueService, UtilisateurService utilisateurService) {
        super();
        this.collegueService = collegueService;
        this.utilisateurService = utilisateurService;
    }

    /**
     * Controleur envoyant en réponse HTTP la liste des matricules des collègues qui ont le nom spécifié par la
     * requête GET.
     *
     * @param nom : String nom du/des collègue(s)
     * @return : réponse http contenant List<String> liste des matricules des
     * collègues
     */
    @RequestMapping(path = "/collegues", method = RequestMethod.GET)
    @ResponseBody
    public List<String> reqParamNom(@RequestParam String nom) {

        List<Collegue> listeCollegues = collegueService.rechercherParNom(nom);

        return listeCollegues.stream().map(Collegue::getMatricule).collect(Collectors.toList());

    }

    /**
     * Controleur envoyant en réponse HTTP le collègue qui a le matricule spécifié par le requête GET.
     *
     * @param matricule : String matricule du collègue
     * @return : réponse http contenant dans le corps : Collegue le collègue
     * correspondant
     */
    @RequestMapping(path = "/collegues/{matricule}", method = RequestMethod.GET)
    @ResponseBody
    public CollegueDtoGet reqMatricule(@PathVariable String matricule) {
        return new CollegueDtoGet(collegueService.rechercherParMatricule(matricule));
    }

    /**
     * Gestionnaire de l'exception CollegueNonTrouveException
     *
     * @param e : CollegueNonTrouveException
     * @return : réponse http avec statut d'erreur 404, contenant dans le corps le message d'erreur
     */
    @ExceptionHandler(CollegueNonTrouveException.class)
    public ResponseEntity<String> handleException(CollegueNonTrouveException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Gestionnaire de l'exception CollegueInvalideException
     *
     * @param e : CollegueInvalideException
     * @return : réponse http avec statut d'erreur 404, contenant dans le corps le message d'erreur
     */
    @ExceptionHandler(CollegueInvalideException.class)
    public ResponseEntity<String> handleException(CollegueInvalideException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    /**
     * Controleur permettant l'ajout d'un collègue dans la Map des collègues à
     * partir des données de la requête POST.
     *
     * @param collegueDto : Collegue collègue à ajouter (JSON)
     * @return : réponse http contenant dans son corps le collègue ajouté
     */
    @PostMapping("/collegues")
    @ResponseBody
    public ResponseEntity<CollegueDtoGet> reqAjoutCollegue(@RequestBody CollegueDtoPost collegueDto) {
        LOGGER.info("reqAjoutCollegue() lancé");
        List<String> roles = new ArrayList<>();
        if(collegueDto.getRole().equalsIgnoreCase("USER")) {
            roles.add(new String("USER"));
        } else if(collegueDto.getRole().equalsIgnoreCase("ADMIN")) {
            roles.add(new String("USER"));
            roles.add(new String("ADMIN"));
        }
        Utilisateur utilisateur = new Utilisateur(collegueDto.getIdentifiant(), collegueDto.getMotDePasse(), roles);
        utilisateurService.ajouterUnUtilisateur(utilisateur);
        Collegue collegue = collegueService.ajouterUnCollegue(new Collegue(UUID.randomUUID().toString(),
                collegueDto.getNom(),
                collegueDto.getPrenoms(), collegueDto.getEmail(), collegueDto.getDateDeNaissance(),
                collegueDto.getPhotoUrl(), utilisateur));
        return ResponseEntity.status(201).body(new CollegueDtoGet(collegue));
    }

    /**
     * Controleur permettant la modification d'un collègue, à partir des données de la requête PATCH.
     *
     * @param collegue  : Collegue données partielles pour la modification (JSON)
     * @param matricule : String matricule du collègue à modifier
     * @return : réponse http contenant dans son corps le collègue après modification
     */
    @PatchMapping("/collegues/{matricule}")
    @ResponseBody
    public CollegueDtoGet partialUpdateName(@RequestBody Collegue collegue,
                                      @PathVariable("matricule") String matricule) {

        if (collegue.getEmail() != null) {
            collegueService.modifierEmail(matricule, collegue.getEmail());
        }
        if (collegue.getPhotoUrl() != null) {
            collegueService.modifierPhotoUrl(matricule, collegue.getPhotoUrl());
        }

        return new CollegueDtoGet(collegueService.rechercherParMatricule(matricule));
    }

}
