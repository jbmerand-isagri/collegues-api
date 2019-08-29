/**
 * 
 */
package dev.collegues_api.service;

import java.time.LocalDate;

import org.junit.Test;

import dev.collegues_api.exception.CollegueInvalideException;
import dev.collegues_api.model.Collegue;

/**
 * 
 */
public class CollegueServiceTest {

	@Test(expected = CollegueInvalideException.class)
	public void test_ajouterUnCollegue_prenom_invalide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegueAAjouter = new Collegue("D", "Lewis", "james.go@mail.com", LocalDate.of(1988, 06, 25),
				"http://www.kfhdg.com");
		collegueService.ajouterUnCollegue(collegueAAjouter);
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_ajouterUnCollegue_nom_invalide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegueAAjouter = new Collegue("Doe", "L", "james.go@mail.com", LocalDate.of(1988, 06, 25),
				"http://www.kfhdg.com");
		collegueService.ajouterUnCollegue(collegueAAjouter);
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_ajouterUnCollegue_mail_invalide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.gomail.com", LocalDate.of(1988, 06, 25),
				"http://www.kfhdg.com");
		collegueService.ajouterUnCollegue(collegueAAjouter);
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_ajouterUnCollegue_mail_invalide2() {
		CollegueService collegueService = new CollegueService();
		Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "ja", LocalDate.of(1988, 06, 25),
				"http://www.kfhdg.com");
		collegueService.ajouterUnCollegue(collegueAAjouter);
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_ajouterUnCollegue_age_invalide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.go@mail.com", LocalDate.of(2018, 06, 25),
				"http://www.kfhdg.com");
		collegueService.ajouterUnCollegue(collegueAAjouter);
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_ajouterUnCollegue_photo_invalide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.go@mail.com", LocalDate.of(1988, 06, 25),
				"www.kfhdg.com");
		collegueService.ajouterUnCollegue(collegueAAjouter);
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_ajouterUnCollegue_photo_invalide2() {
		CollegueService collegueService = new CollegueService();
		Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.go@mail.com", LocalDate.of(1988, 06, 25), "m");
		collegueService.ajouterUnCollegue(collegueAAjouter);
	}

}
