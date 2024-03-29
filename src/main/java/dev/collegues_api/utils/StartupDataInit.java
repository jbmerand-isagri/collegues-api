package dev.collegues_api.utils;

import dev.collegues_api.model.Collegue;
import dev.collegues_api.model.Utilisateur;
import dev.collegues_api.repository.CollegueRepository;
import dev.collegues_api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Component
public class StartupDataInit {

    @Autowired
    private CollegueRepository collegueRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {

        // insertion de 2 utilisateurs en base de données
        utilisateurRepository.save(new Utilisateur("u1", passwordEncoder.encode("pass1"), Arrays.asList("ROLE_ADMIN", "ROLE_USER")));
        utilisateurRepository.save(new Utilisateur("u2",  passwordEncoder.encode("pass2"), Arrays.asList("ROLE_USER")));
        utilisateurRepository.save(new Utilisateur("u3", passwordEncoder.encode("pass3"), Arrays.asList("ROLE_ADMIN",
                "ROLE_USER")));
        utilisateurRepository.save(new Utilisateur("u4", passwordEncoder.encode("pass4"), Arrays.asList("ROLE_ADMIN",
                "ROLE_USER")));
        utilisateurRepository.save(new Utilisateur("u5", passwordEncoder.encode("pass5"), Arrays.asList(
                "ROLE_ADMIN",
                "ROLE_USER")));

        Collegue collegue1 = new Collegue(UUID.randomUUID().toString(), "Dupuis", "James, Arnold",
                "james.dupuis@mail.com", LocalDate.of(1988, 6, 25), "https://media.istockphoto.com/photos/factory-worker-controlling-production-picture-id930963632?k=6&m=930963632&s=612x612&w=0&h=9-qBlfH1bGiVD7r1IJoYtMM-3LI1_P5y_09yankChXc=",
                utilisateurRepository.findByIdentifiant("u1").get());
        Collegue collegue2 = new Collegue(UUID.randomUUID().toString(), "Durand", "Oliver, Pierre",
                "oliver.durand@mail.com", LocalDate.of(1980, 2, 29), "https://pedreirao.com.br/wp-content/uploads/2014/06/8-dicas-engenheiro-civil-arquiteto-recem-formado-pedreirao.jpg",
                utilisateurRepository.findByIdentifiant("u2").get());
        Collegue collegue3 = new Collegue(UUID.randomUUID().toString(), "Monk", "Bernard", "bernard.monk@mail.com",
                LocalDate.of(1990, 12, 4), "https://www.greateasternlife.com/content/GreatEastern/my/en/index/corporate-solutions/general-insurance/foreign-worker-hospitalisation-and-surgical-scheme/_jcr_content/contentPar2/image.img.jpg/1495163981442.jpg", utilisateurRepository.findByIdentifiant("u3").get());
        Collegue collegue4 = new Collegue(UUID.randomUUID().toString(), "Martin", "Eléonore, Alice",
                "eleonore.martin@mail.com", LocalDate.of(1978, 3, 12), "https://www.fairobserver.com/wp-content/uploads/2017/08/Factory-workers-America-news-manufacturing-jobs-world-news-1.jpg",
                utilisateurRepository.findByIdentifiant("u4").get());
        Collegue collegue5 = new Collegue(UUID.randomUUID().toString(), "Durand", "Didier", "didier.durand@mail.com",
                LocalDate.of(1965, 11, 15), "https://ak2.picdn.net/shutterstock/videos/21399202/thumb/1.jpg",
                utilisateurRepository.findByIdentifiant("u5").get());

        collegueRepository.save(collegue1);
        collegueRepository.save(collegue2);
        collegueRepository.save(collegue3);
        collegueRepository.save(collegue4);
        collegueRepository.save(collegue5);

    }
}
