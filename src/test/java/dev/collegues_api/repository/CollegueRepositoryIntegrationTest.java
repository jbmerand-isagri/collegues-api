package dev.collegues_api.repository;

import dev.collegues_api.model.Collegue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CollegueRepositoryIntegrationTest {

    @Autowired
    private CollegueRepository collegueRepository;

    @Test
    public void test_findByNom_cas_un_nom() {

        collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "LewisXZAOPMSDNCSFDSF", "Oliver, Pierre",
                "oliver.durand@mail.com", LocalDate.of(1980, 02, 29), "https://dsggfd.com"));

        List<Collegue> listeDesCollegues = collegueRepository.findByNomIgnoreCase("LewisXZAOPMSDNCSFDSF");
        assertEquals(1, listeDesCollegues.size());
    }

    @Test
    public void test_findByNom_cas_nom_non_trouve() {

        List<Collegue> listeDesCollegues = collegueRepository.findByNomIgnoreCase("BornusJHDJFHFHBXVBLKJJ");
        assertEquals(0, listeDesCollegues.size());
    }

    @Test
    public void test_FindByNom_cas_deux_noms() {
        collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "LukeXZAOPMSDNCSFDSF", "Oliver, Pierre",
                "oliver.durand@mail.com", LocalDate.of(1980, 2, 29), "https://dsggfd.com"));

        collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "LukeXZAOPMSDNCSFDSF", "Joseph",
                "joseph.lewis@mail.com", LocalDate.of(1987, 9, 24), "https://asgfd.com"));
        List<Collegue> listeDesCollegues = collegueRepository.findByNomIgnoreCase("LukeXZAOPMSDNCSFDSF");
        assertEquals(2, listeDesCollegues.size());
    }

}
