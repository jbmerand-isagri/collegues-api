package dev.collegues_api.service;

import dev.collegues_api.exception.CollegueInvalideException;
import dev.collegues_api.exception.CollegueNonTrouveException;
import dev.collegues_api.model.Collegue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest // charge un contexte Spring Boot complet pour le test
public class CollegueServiceIntegrationTest {

    @Autowired
    private CollegueService collegueService;

    /*@Test // exemple de test applicable
    public void testCombien() {
        long resultat = collegueService.combien();
        Assert.assertEquals(1, resultat);
    }*/

    @Test
    public void test_ajouterUnCollegue_valide() {

        Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.go@mail.com", LocalDate.of(1988, 06, 25),
                "http://www.kfhdg.com");
        collegueService.ajouterUnCollegue(collegueAAjouter);
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ajouterUnCollegue_prenom_invalide() {
        Collegue collegueAAjouter = new Collegue("D", "Lewis", "james.go@mail.com", LocalDate.of(1988, 06, 25),
                "http://www.kfhdg.com");
        collegueService.ajouterUnCollegue(collegueAAjouter);
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ajouterUnCollegue_nom_invalide() {
        Collegue collegueAAjouter = new Collegue("Doe", "L", "james.go@mail.com", LocalDate.of(1988, 06, 25),
                "http://www.kfhdg.com");
        collegueService.ajouterUnCollegue(collegueAAjouter);
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ajouterUnCollegue_mail_invalide() {
        Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.gomail.com", LocalDate.of(1988, 06, 25),
                "http://www.kfhdg.com");
        collegueService.ajouterUnCollegue(collegueAAjouter);
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ajouterUnCollegue_mail_invalide2() {
        Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "ja", LocalDate.of(1988, 06, 25),
                "http://www.kfhdg.com");
        collegueService.ajouterUnCollegue(collegueAAjouter);
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ajouterUnCollegue_age_invalide() {
        Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.go@mail.com", LocalDate.of(2018, 06, 25),
                "http://www.kfhdg.com");
        collegueService.ajouterUnCollegue(collegueAAjouter);
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ajouterUnCollegue_photo_invalide() {
        Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.go@mail.com", LocalDate.of(1988, 06, 25),
                "www.kfhdg.com");
        collegueService.ajouterUnCollegue(collegueAAjouter);
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_ajouterUnCollegue_photo_invalide2() {
        Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.go@mail.com", LocalDate.of(1988, 06, 25), "m");
        collegueService.ajouterUnCollegue(collegueAAjouter);
    }

    @Test
    public void test_modifierEmail_email_valide() {
        Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.of(1980, 7, 21), "http;//www.dubois.com");
        collegueService.ajouterUnCollegue(collegue0);
        collegueService.modifierEmail("HDFJ-765-YSD", "mail@xxxx.org");

        Collegue collegueAModifier = collegueService.rechercherParMatricule("HDFJ-765-YSD");
        assertEquals("mail@xxxx.org", (collegueAModifier.getEmail()));
    }

    @Test(expected = CollegueNonTrouveException.class)
    public void test_modifierEmail_matricule_invalide() {
        collegueService.modifierEmail(UUID.randomUUID().toString(), "jcfcfdgh@dfg.com");
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_modifierEmail_email_invalide() {
        Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.of(1980, 7, 21), "http;//www.dubois.com");
        collegueService.ajouterUnCollegue(collegue0);
        collegueService.modifierEmail("HDFJ-765-YSD", "j");
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_modifierEmail_email_invalide2() {
        Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.of(1980, 7, 21), "http;//www.dubois.com");
        collegueService.ajouterUnCollegue(collegue0);
        collegueService.modifierEmail("HDFJ-765-YSD", "jsdfdsf.com");
    }

    @Test
    public void test_modifierPhotoUrl_url_valide() {
        Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.of(1980, 7, 21), "http;//www.dubois.com");
        collegueService.ajouterUnCollegue(collegue0);
        collegueService.modifierPhotoUrl("HDFJ-765-YSD", "http://dfudfjk.org");

        assertEquals("http://dfudfjk.org", collegueService.rechercherParMatricule("HDFJ-765-YSD").getPhotoUrl());
    }

    @Test(expected = CollegueInvalideException.class)
    public void test_modifierPhotoUrl_url_invalide() {
        Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.of(1980, 7, 21), "http;//www.dubois.com");
        collegueService.ajouterUnCollegue(collegue0);
        collegueService.modifierPhotoUrl(collegue0.getMatricule(), "www.dfudfjk.org");
    }

    @Test(expected = CollegueNonTrouveException.class)
    public void test_modifierPhotoUrl_matricule_invalide() {
        Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.of(1980, 7, 21), "http;//www.dubois.com");
        collegueService.ajouterUnCollegue(collegue0);
        collegueService.modifierPhotoUrl("6T5T6-JSHDF", "http://dfudfjk.org");
    }
}
