/**
 * 
 */
package dev.collegues_api.service;

import java.time.LocalDate;

import org.junit.Test;

import dev.collegues_api.exception.CollegueInvalideException;
import dev.collegues_api.exception.CollegueNonTrouveException;
import dev.collegues_api.model.Collegue;

/**
 * Classe de tests des méthodes de CollegueService
 */
public class CollegueServiceTest {

	@Test
	public void test_ajouterUnCollegue_valide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegueAAjouter = new Collegue("Doe", "Lewis", "james.go@mail.com", LocalDate.of(1988, 06, 25),
				"http://www.kfhdg.com");
		collegueService.ajouterUnCollegue(collegueAAjouter);
	}

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

	@Test
	public void test_modifierEmail_email_valide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
				LocalDate.of(1980, 07, 21), "http;//www.dubois.com");
		collegueService.getData().put(collegue0.getMatricule(), collegue0);

		collegueService.modifierEmail(collegue0.getMatricule(), "jsdf@dsf.com");
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void test_modifierEmail_matricule_invalide() {
		CollegueService collegueService = new CollegueService();

		collegueService.modifierEmail("matricule", "jcfcfdgh@dfg.com");
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_modifierEmail_email_invalide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
				LocalDate.of(1980, 07, 21), "http;//www.dubois.com");
		collegueService.getData().put(collegue0.getMatricule(), collegue0);

		collegueService.modifierEmail(collegue0.getMatricule(), "j");
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_modifierEmail_email_invalide2() {
		CollegueService collegueService = new CollegueService();
		Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
				LocalDate.of(1980, 07, 21), "http;//www.dubois.com");
		collegueService.getData().put(collegue0.getMatricule(), collegue0);

		collegueService.modifierEmail(collegue0.getMatricule(), "jsdfdsf.com");
	}

	@Test
	public void test_modifierPhotoUrl_url_valide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
				LocalDate.of(1980, 07, 21), "http;//www.dubois.com");
		collegueService.getData().put(collegue0.getMatricule(), collegue0);

		collegueService.modifierPhotoUrl(collegue0.getMatricule(), "http://dfudfjk.org");
	}

	@Test(expected = CollegueInvalideException.class)
	public void test_modifierPhotoUrl_url_invalide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
				LocalDate.of(1980, 07, 21), "http;//www.dubois.com");
		collegueService.getData().put(collegue0.getMatricule(), collegue0);

		collegueService.modifierPhotoUrl(collegue0.getMatricule(), "www.dfudfjk.org");
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void test_modifierPhotoUrl_matricule_invalide() {
		CollegueService collegueService = new CollegueService();
		Collegue collegue0 = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
				LocalDate.of(1980, 07, 21), "http;//www.dubois.com");
		collegueService.getData().put(collegue0.getMatricule(), collegue0);

		collegueService.modifierPhotoUrl("6T5T6-JSHDF", "http://dfudfjk.org");
	}

}
