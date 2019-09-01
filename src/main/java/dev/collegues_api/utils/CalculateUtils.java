package dev.collegues_api.utils;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

/**
 * Classe utilitaire contenant des méthodes de calcul.
 */
@Component
public class CalculateUtils {

	/**
	 * Méthode pour calculer l'âge d'une personne à partir de sa date de naissance.
	 * 
	 * @param birthDate : LocalDate la date de naissance de la personne
	 * @return : int l'âge de la personne
	 */
	public int calculateAge(LocalDate birthDate) {
		// validate inputs ...
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
}
