package dev.collegues_api.utils;

import dev.collegues_api.model.Collegue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CollegueValidator.class, CalculateUtils.class}) // lance un contexte précis
public class CollegueValidatorTest {
/*
    @Value(("${ext.url}"))
    private String url;*/

    @Autowired
    private CollegueValidator collegueValidator;

    @Test
    public void test_isCollegueInfosCorrectes_cas_infos_correctes() {
        Collegue collegue = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.now().minus(Period.ofYears(24)), "http://www.dubois.com");
        assertTrue(collegueValidator.isCollegueInfosCorrectes(collegue));
    }

    @Test
    public void test_isCollegueInfosCorrectes_cas_age_incorrect() {
        Collegue collegue = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.now().minus(Period.ofYears(15)), "http://www.dubois.com");
        assertFalse(collegueValidator.isCollegueInfosCorrectes(collegue));
    }

    @Test
    public void test_isCollegueInfosCorrectes_cas_nom_incorrect() {
        Collegue collegue = new Collegue("HDFJ-765-YSD", "D", "François", "francois.dubois@mail.com",
                LocalDate.now().minus(Period.ofYears(20)), "http://www.dubois.com");
        assertFalse(collegueValidator.isCollegueInfosCorrectes(collegue));
    }

    @Test
    public void test_isCollegueInfosCorrectes_cas_prenom_incorrect() {
        Collegue collegue = new Collegue("HDFJ-765-YSD", "Dubois", "F", "francois.dubois@mail.com",
                LocalDate.now().minus(Period.ofYears(20)), "http://www.dubois.com");
        assertFalse(collegueValidator.isCollegueInfosCorrectes(collegue));
    }

    @Test
    public void test_isCollegueInfosCorrectes_cas_email_incorrect() {
        Collegue collegue = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.duboismail.com",
                LocalDate.now().minus(Period.ofYears(20)), "http://www.dubois.com");
        assertFalse(collegueValidator.isCollegueInfosCorrectes(collegue));
    }

    @Test
    public void test_isCollegueInfosCorrectes_cas_url_photo_incorrecte() {
        Collegue collegue = new Collegue("HDFJ-765-YSD", "Dubois", "François", "francois.dubois@mail.com",
                LocalDate.now().minus(Period.ofYears(20)), "ht://www.dubois.com");
        assertFalse(collegueValidator.isCollegueInfosCorrectes(collegue));
    }

    @Test
    public void test_isCollegueInfosCorrectes_cas_email_incorrect_2() {
        Collegue collegue = new Collegue("HDFJ-765-YSD", "Dubois", "François", "f@",
                LocalDate.now().minus(Period.ofYears(20)), "http://www.dubois.com");
        assertFalse(collegueValidator.isCollegueInfosCorrectes(collegue));
    }

}
