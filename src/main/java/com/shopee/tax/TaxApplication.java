package com.shopee.tax;

import com.shopee.tax.entity.Tax;
import com.shopee.tax.repository.TaxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class TaxApplication implements CommandLineRunner {

	@Autowired
	private TaxRepository taxRepository;

	private static Logger LOG = LoggerFactory.getLogger(TaxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tax tax1 = new Tax("Lucky Stretch", 2, new BigDecimal(1000));
		Tax tax2 = new Tax("Big Mac", 1, new BigDecimal(1000));
		Tax tax3 = new Tax("Movie", 3, new BigDecimal(1000));

		taxRepository.save(tax1);
		taxRepository.save(tax2);
		taxRepository.save(tax3);
	}
}
