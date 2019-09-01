package dev.collegues_api.utils;

import dev.collegues_api.model.Collegue;
import dev.collegues_api.repository.CollegueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class StartupDataInit {

    @Autowired
    private CollegueRepository collegueRepository;

    // La méthode init va être invoquée au démarrage de l'application.
    @EventListener(ContextRefreshedEvent.class)
    public void init() {

        Collegue collegue1 = new Collegue(UUID.randomUUID().toString(), "Dupuis", "James, Arnold",
                "james.dupuis@mail.com", LocalDate.of(1988, 06, 25), "https://dsggfd.com");
        Collegue collegue2 = new Collegue(UUID.randomUUID().toString(), "Durand", "Oliver, Pierre",
                "oliver.durand@mail.com", LocalDate.of(1980, 02, 29), "https://dsggfd.com");
        Collegue collegue3 = new Collegue(UUID.randomUUID().toString(), "Monk", "Bernard", "bernard.monk@mail.com",
                LocalDate.of(1990, 12, 04), "https://dsggfd.com");
        Collegue collegue4 = new Collegue(UUID.randomUUID().toString(), "Martin", "Eléonore, Alice",
                "eleonore.martin@mail.com", LocalDate.of(1978, 03, 12), "https://dsggfd.com");
        Collegue collegue5 = new Collegue(UUID.randomUUID().toString(), "Durand", "Didier", "didier.durand@mail.com",
                LocalDate.of(1965, 11, 15), "https://dsggfd.com");

        collegueRepository.save(collegue1);
        collegueRepository.save(collegue2);
        collegueRepository.save(collegue3);
        collegueRepository.save(collegue4);
        collegueRepository.save(collegue5);

    }
}
