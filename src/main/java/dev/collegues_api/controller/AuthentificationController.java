package dev.collegues_api.controller;

import dev.collegues_api.exception.UtilisateurNonTrouveException;
import dev.collegues_api.model.Collegue;
import dev.collegues_api.model.Utilisateur;
import dev.collegues_api.repository.UtilisateurRepository;
import dev.collegues_api.service.CollegueService;
import dev.collegues_api.service.UtilisateurService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class AuthentificationController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthentificationController.class);

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    private UtilisateurRepository utilisateurRepository;

    private UtilisateurService utilisateurService;

    private PasswordEncoder passwordEncoder;

    private CollegueService collegueService;

    @Autowired
    public AuthentificationController(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder,
                                      UtilisateurService utilisateurService, CollegueService collegueService) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilisateurService = utilisateurService;
        this.collegueService = collegueService;
    }

    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity<?> authenticate(@RequestBody Utilisateur user) {
        LOGGER.info("authenticate() lancé");

        return this.utilisateurRepository.findByIdentifiant(user.getIdentifiant())
                .filter(utilisateur -> passwordEncoder.matches(user.getMotDePasse(), utilisateur.getMotDePasse()))
                .map(utilisateur -> {
                    Map<String, Object> infosSupplementaireToken = new HashMap<>();
                    infosSupplementaireToken.put("roles", utilisateur.getRoles());

                    String jetonJWT = Jwts.builder()
                            .setSubject(utilisateur.getIdentifiant())
                            .addClaims(infosSupplementaireToken)
                            .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
                            .signWith(SignatureAlgorithm.HS512, SECRET)
                            .compact();

                    ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT)
                            .httpOnly(true)
                            .maxAge(EXPIRES_IN * 1000)
                            .path("/")
                            .build();

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                            .build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/auth-statut")
    public ModelAndView reqAfficherStatutAuthentification() {
        ModelAndView mv = new ModelAndView();
        try {
            Collegue collegue = collegueService.rechercherCollegueParUtilisateur(utilisateurService.recuperUtilisateurParIdentifiant(SecurityContextHolder.getContext().getAuthentication().getName()));
            List<String> liste = new ArrayList<>();
            liste.add("matricule = " + collegue.getMatricule());
            liste.add("email = " + collegue.getEmail());
            liste.add("nom = " + collegue.getNom());
            liste.add("prenoms = " + collegue.getPrenoms());

            mv.addObject("utilisateurConnecte", liste);
        } catch(UtilisateurNonTrouveException e) {
            mv.addObject("utilisateurConnecte",
                    new String("Erreur. Aucun utilisateur authentifié ? \n" + e.getMessage()));
        }

        mv.setViewName("jsp/authentification-statut");
        return mv;
    }

    @GetMapping("/auth")
    public String reqAfficherVueAuthentification() {
        return "jsp/authentification";
    }

}
