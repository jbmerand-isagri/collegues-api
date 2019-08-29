package dev.collegues_api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.collegues_api.exception.CollegueInvalideException;
import dev.collegues_api.exception.CollegueNonTrouveException;
import dev.collegues_api.model.Collegue;
import dev.collegues_api.utils.CalculateUtils;

/**
 * Classe gérant l'ensemble des services qui concernent les collègues.
 */
public class CollegueService {

	/** LOGGER : Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CollegueService.class);

	/** data : Map<String,Collegue> */
	private Map<String, Collegue> data = new HashMap<>();

	/**
	 * Constructor
	 * 
	 */
	public CollegueService() {
		// TODO alimenter data avec des données fictives
		// Pour générer un matricule : `UUID.randomUUID().toString()`

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

		data.put(collegue1.getMatricule(), collegue1);
		data.put(collegue2.getMatricule(), collegue2);
		data.put(collegue3.getMatricule(), collegue3);
		data.put(collegue4.getMatricule(), collegue4);
		data.put(collegue5.getMatricule(), collegue5);

	}

	/**
	 * Méthode pour retourner une liste des collègues qui ont le nom précisé.
	 * 
	 * @param nomRecherche : String nom du collègue
	 * @return List<Collegue> Liste des collègues avec ce nom.
	 */
	public List<Collegue> rechercherParNom(String nomRecherche) {
		// TODO retourner une liste de collègues dont le nom est fourni
		LOGGER.info("rechercheParNom() lancée");
		List<Collegue> listeCollegues = new ArrayList<>();

		for (Map.Entry<String, Collegue> entry : data.entrySet()) {
			if ((entry.getValue().getNom()).equalsIgnoreCase(nomRecherche)) {
				listeCollegues.add(entry.getValue());
			}

		}
		return listeCollegues;
	}

	/**
	 * Méthode pour retourner le collègue correspondant au matricule entré.
	 * 
	 * @param matriculeRecherche : String matricule du collègue
	 * @return : Collegue collègue dont le matricule correspond
	 * @throws CollegueNonTrouveException : Exception si jamais aucun collègue ne
	 *                                    correspond au matricule.
	 */
	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
		// TODO retourner le collègue dont le matricule est fourni
		// TODO retourner une exception `CollegueNonTrouveException` (à créer)
		// si le matricule ne correspond à aucun collègue
		LOGGER.info("rechercheParMatricule() lancée");
		Collegue collegue = null;
		Boolean isMatriculeTrouve = false;

		for (Map.Entry<String, Collegue> entry : data.entrySet()) {
			if ((entry.getValue().getMatricule()).equalsIgnoreCase(matriculeRecherche)) {
				collegue = entry.getValue();
				isMatriculeTrouve = true;
			}
		}

		if (isMatriculeTrouve != true) {
			LOGGER.info("rechercherParMatricule -> throw CollegueNonTrouveException");
			throw new CollegueNonTrouveException("Ce matricule ne correspond à aucun collègue.");
		}

		return collegue;

	}

	/**
	 * Méthode pour ajouter un collègue dans la Map de tous les collègues de
	 * l'application.
	 * 
	 * @param collegueAAjouter : Collegue le collègue à ajouter
	 * @return : Collegue le collègue ajouté
	 * @throws CollegueInvalideException : Exception dans le cas où des données du
	 *                                   collègue à ajouter ne répondent pas au
	 *                                   format imposé.
	 */
	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {

		LOGGER.info("ajouterUnCollegue() lancée");
		// TODO Vérifier que le nom et les prenoms ont chacun au moins 2 caractères
		// TODO Vérifier que l'email a au moins 3 caractères et contient `@`
		// TODO Vérifier que la photoUrl commence bien par `http`
		// TODO Vérifier que la date de naissance correspond à un age >= 18
		// TODO Si une des règles ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`.
		// TODO générer un matricule pour ce collègue (`UUID.randomUUID().toString()`)
		// TODO Sauvegarder le collègue
		if (collegueAAjouter.getNom().length() >= 2 && collegueAAjouter.getPrenoms().length() >= 2
				&& collegueAAjouter.getEmail().length() >= 3 && collegueAAjouter.getEmail().contains("@")
				&& collegueAAjouter.getPhotoUrl().toLowerCase().startsWith("http")
				&& CalculateUtils.calculateAge(collegueAAjouter.getDateDeNaissance()) >= 18) {
			collegueAAjouter.setMatricule(UUID.randomUUID().toString());
			data.put(collegueAAjouter.getMatricule(), collegueAAjouter);
			return collegueAAjouter;
		} else {
			LOGGER.info("ajouterUnCollegue -> throw CollegueInvalidationException");
			throw new CollegueInvalideException(
					"Impossible d'ajouter le collègue. Au moins une de ses données ne répond pas aux attentes.");
		}

	}

	/**
	 * Méthode pour modifier l'email d'un collègue de la Map stockant tous les
	 * collègues.
	 * 
	 * @param matricule : String le matricule du collègue
	 * @param email     : String le nouvel email du collègue
	 * @return : Collegue le collègue modifié
	 */
	public Collegue modifierEmail(String matricule, String email) {

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue
		Collegue collegue = rechercherParMatricule(matricule);

		// TODO Vérifier que l'email a au moins 3 caractères et contient `@`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.
		if (email.length() < 3 || !email.contains("@")) {
			throw new CollegueInvalideException("Email incorrect : il ne respecte pas le format.");
		} else {
			// TODO Modifier le collègue
			collegue.setEmail(email);
		}

		return collegue;
	}

	/**
	 * Méthode pour modifier l'url de la photo d'un collègue de la Map stockant tous
	 * les collègues.
	 * 
	 * @param matricule : String matricule du collègue
	 * @param photoUrl  : String le nouvel url de la photo du collègue
	 * @return : Collegue le collègue amodifié
	 */
	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue
		Collegue collegue = rechercherParMatricule(matricule);

		// TODO Vérifier que la photoUrl commence bien par `http`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.
		if (!photoUrl.startsWith("http")) {
			throw new CollegueInvalideException("Url de la photo incorrecte : elle ne commence pas par http.");
		} else {
			// TODO Modifier le collègue
			collegue.setPhotoUrl(photoUrl);
		}

		return collegue;
	}

	/**
	 * Getter
	 * 
	 * @return the data
	 */
	public Map<String, Collegue> getData() {
		return data;
	}

	/**
	 * Setter
	 * 
	 * @param data the data to set
	 */
	public void setData(Map<String, Collegue> data) {
		this.data = data;
	}

}
