package dev.collegues_api.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest // charge un contexte Spring Boot complet pour le test
public class CollegueServiceIntegrationTest {

    private CollegueService collegueService;

    @Test
    public void testCombien() {
        long resultat = collegueService.combien();
        Assert.assertEquals(1, resultat);
    }
}
