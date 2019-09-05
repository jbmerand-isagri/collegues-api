package dev.collegues_api.controller;

import dev.collegues_api.controller.dto.CollegueDtoGet;
import dev.collegues_api.model.Collegue;
import dev.collegues_api.service.CollegueService;
import dev.collegues_api.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController {

    private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private CollegueService collegueService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/auth/user")
    public CollegueDtoGet reqInfosCollegueConnecte() {
        LOGGER.info("reqInfosCollegueConnecte() lancé");
        LOGGER.info("" + collegueService.rechercherCollegueParUtilisateur(utilisateurService.recuperUtilisateurParIdentifiant(SecurityContextHolder.getContext().getAuthentication().getName())));
        Collegue collegue = collegueService.rechercherCollegueParUtilisateur(utilisateurService.recuperUtilisateurParIdentifiant(SecurityContextHolder.getContext().getAuthentication().getName()));
        return new CollegueDtoGet(collegue);
    }

}
