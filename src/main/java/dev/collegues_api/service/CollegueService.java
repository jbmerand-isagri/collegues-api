package dev.collegues_api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dev.collegues_api.exception.CollegueNonTrouveException;
import dev.collegues_api.model.Collegue;

public class CollegueService {

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

		Collegue collegue = null;
		Boolean isMatriculeTrouve = false;

		for (Map.Entry<String, Collegue> entry : data.entrySet()) {
			if ((entry.getValue().getMatricule()).equalsIgnoreCase(matriculeRecherche)) {
				collegue = entry.getValue();
				isMatriculeTrouve = true;
			}
		}

		if (isMatriculeTrouve != true) {
			throw new CollegueNonTrouveException("Ce matricule ne correspond à aucun collègue.");
		}

		return collegue;

	}
}
