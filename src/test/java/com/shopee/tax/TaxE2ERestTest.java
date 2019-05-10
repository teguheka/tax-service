package com.shopee.tax;

import com.shopee.tax.entity.Tax;
import com.shopee.tax.repository.TaxRepository;
import com.shopee.tax.service.TaxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaxE2ERestTest {
    @Autowired private TaxRepository taxRepository;
    @LocalServerPort private int port;

    @After
    public void tearDown() {
        taxRepository.deleteAll();
    }

    @Test
    public void shouldReturnNewData() {
        Tax tax = new Tax("Ayam", 1, new BigDecimal(8000));
        taxRepository.save(tax);

        when()
                .get(String.format("http://localhost:%s/api/v1/taxes", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Ayam"));
    }
}
