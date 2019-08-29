package dev.collegues_api.utils;

import java.time.LocalDate;
import java.time.Period;

public class CalculateUtils {

	public static int calculateAge(LocalDate birthDate) {
		// validate inputs ...
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
}
