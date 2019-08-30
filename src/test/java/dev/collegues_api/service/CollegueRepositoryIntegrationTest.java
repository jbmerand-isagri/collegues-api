package dev.collegues_api.service;

import dev.collegues_api.model.Collegue;
import dev.collegues_api.repository.CollegueRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class) // délègue exécution du test à Spring
@DataJpaTest
public class CollegueRepositoryIntegrationTest {

    @Autowired
    private CollegueRepository collegueRepository;

    @Test
    public void testFindByNom() {

        collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "Lewis", "Oliver, Pierre",
                "oliver.durand@mail.com", LocalDate.of(1980, 02, 29), "https://dsggfd.com"));

        List<Collegue> listeDesCollegues = collegueRepository.findByNomIgnoreCase("Lewis");
        Assert.assertEquals(1, listeDesCollegues.size());
    }

    @Test
    public void testFindByNomCasNomNonTrouve() {

        collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "Lewis", "Oliver, Pierre",
                "oliver.durand@mail.com", LocalDate.of(1980, 02, 29), "https://dsggfd.com"));

        List<Collegue> listeDesCollegues = collegueRepository.findByNomIgnoreCase("Bornus");
        Assert.assertEquals(0, listeDesCollegues.size());
    }

}
