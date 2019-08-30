package dev.collegues_api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.collegues_api.exception.CollegueInvalideException;
import dev.collegues_api.model.Collegue;

@Component
public class CollegueValidator {

	/** LOGGER : Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CollegueValidator.class);

	public Boolean isCollegueInfosCorrectes(Collegue collegue) throws CollegueInvalideException {
		if (collegue.getNom().length() >= 2 && collegue.getPrenoms().length() >= 2 && collegue.getEmail().length() >= 3
				&& collegue.getEmail().contains("@") && collegue.getPhotoUrl().toLowerCase().startsWith("http")
				&& CalculateUtils.calculateAge(collegue.getDateDeNaissance()) >= 18) {
			return true;
		} else {
			throw new CollegueInvalideException(
					"Impossible d'ajouter le collègue. Au moins une de ses données ne répond pas aux attentes.");
		}
	}

}
