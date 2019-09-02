package dev.collegues_api.utils;

import dev.collegues_api.model.Collegue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollegueValidator {

    /**
     * LOGGER : Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CollegueValidator.class);

    @Autowired
    private CalculateUtils calculateUtils;

    public Boolean isCollegueInfosCorrectes(Collegue collegue) {
        return collegue.getNom().length() >= 2 && collegue.getPrenoms().length() >= 2 && collegue.getEmail().length() >= 3
                && collegue.getEmail().contains("@") && collegue.getPhotoUrl().toLowerCase().startsWith("http")
                && calculateUtils.calculateAge(collegue.getDateDeNaissance()) >= 18;
    }

    public Boolean isFormatEmailCorrect(String email) {
        return email.length() >= 3 && email.contains("@");
    }

    public Boolean isFormatUrlPhotoCorrect(String url) {
        return url.startsWith("http");
    }
}