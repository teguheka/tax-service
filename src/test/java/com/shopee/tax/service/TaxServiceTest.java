package com.shopee.tax.service;

import com.shopee.tax.entity.Tax;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Integration and unit test in tax service test will be in here
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxServiceTest {
    @Autowired private TaxService taxService;

    @After
    public void tearDown() {
        taxService.deleteAllData();
    }

    @Test
    public void shouldSaveAndFetchTax() {
        Tax playstation = new Tax("Playstation", 3, new BigDecimal(4000000));
        taxService.create(playstation);

        Tax maybePlaystation = taxService.findByName("Playstation").orElse(null);

        assertThat(maybePlaystation.getName(), is(playstation.getName()));
    }

    @Test
    public void calculateTaxFood() {
        //has rules 10% of Price
        BigDecimal taxedFood = taxService.calculateTax(new BigDecimal(1000), 1);
        double d = taxedFood.doubleValue();

        // assert that can't compare type data BigDecimal, so I convert to double first
        assertThat(d, is(100.0));
    }

    @Test
    public void calculateTaxTobacco() {
        //has rules 10 + (2% of Price )
        BigDecimal taxedTobacco = taxService.calculateTax(new BigDecimal(1000), 2);
        double d = taxedTobacco.doubleValue();

        // assert that can't compare type data BigDecimal, so I convert to double first
        assertThat(d, is(30.0));
    }

    @Test
    public void calculateTaxEntertainPriceMoreThan100() {
        // has rules
        // 0 < Price < 100: tax-free
        // Price >= 100: 1% of ( Price - 100)
        BigDecimal taxedEntertain = taxService.calculateTax(new BigDecimal(1000), 3);
        double d = taxedEntertain.doubleValue();

        // assert that can't compare type data BigDecimal, so I convert to double first
        assertThat(d, is(9.0));
    }

    @Test
    public void calculateTaxEntertainPriceUnder100() {
        // has rules
        // 0 < Price < 100: tax-free
        // Price >= 100: 1% of ( Price - 100)
        BigDecimal taxedEntertain = taxService.calculateTax(new BigDecimal(99), 3);
        double d = taxedEntertain.doubleValue();

        // assert that can't compare type data BigDecimal, so I convert to double first
        assertThat(d, is(0.0));
    }

    @Test
    public void isRefundableFood() {
        // food and beverage is refundable
        Boolean isRefundable = taxService.isRefundable(1);
        assertThat(isRefundable, is(Boolean.TRUE));
    }

    @Test
    public void isRefundableTobacco() {
        // tobacco is Not Refundable
        Boolean isRefundable = taxService.isRefundable(2);
        assertThat(isRefundable, is(Boolean.FALSE));
    }

    @Test
    public void isRefundableEntertain() {
        // entertain is Not Refundable
        Boolean isRefundable = taxService.isRefundable(3);
        assertThat(isRefundable, is(Boolean.FALSE));
    }
}
