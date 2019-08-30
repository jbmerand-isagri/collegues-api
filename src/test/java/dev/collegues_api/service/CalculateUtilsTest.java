package dev.collegues_api.service;

import dev.collegues_api.utils.CalculateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CalculateUtils.class) // lance un contexte précis
public class CalculateUtilsTest {

  /*  @Value(("${ext.url}"))
    private String url;


    @Autowired
    private CalculateUtils calculateUtils;

    @Test
    public void testAdd() {
        calculateUtils.add();
    }
    */

}
