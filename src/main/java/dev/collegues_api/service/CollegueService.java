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

public class CollegueService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CollegueService.class);

	private Map<String, Collegue> data = new HashMap<>();

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

}
