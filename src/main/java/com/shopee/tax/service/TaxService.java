package com.shopee.tax.service;

import com.shopee.tax.entity.Tax;
import com.shopee.tax.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaxService {

    private String[] categories = { "food", "tobacco", "entertainment" };

    @Autowired private TaxRepository taxRepository;

    public Tax create(Tax tax){
        return taxRepository.save(tax);
    }

    public List<Tax> list(){
        List<Tax> taxes = new ArrayList<>();
        taxRepository.findAll().forEach(taxes::add);

        for(Tax t : taxes){
            //set type 1 = food, 2 = tobacco, 3 = entertainment
            String type = categories[t.getTaxCode() - 1];
            t.setType(type);

            //calculate and set tax
            BigDecimal tax = calculateTax(t.getPrice(), t.getTaxCode());
            t.setTax(tax);

            //set refundable
            Boolean refundable = isRefundable(t.getTaxCode());
            t.setRefundable(refundable ? "Yes": "No");

            //calculate total amount (tax + price)
            BigDecimal amount = t.getPrice().add(tax);
            t.setAmount(amount);
        }
        return taxes;
    }

    public BigDecimal getSubTotalPrice(List<Tax> taxes) {
        return taxes.stream().map(Tax::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSubTotalTax(List<Tax> taxes) {
        return taxes.stream().map(Tax::getTax).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getSubTotalAmount(List<Tax> taxes) {
        return taxes.stream().map(Tax::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * count tax
     * @param price
     * @param taxCode
     * @return
     */
    public BigDecimal calculateTax(BigDecimal price, Integer taxCode) {
        BigDecimal tax;
        switch (taxCode) {
            case 1: // food and beverage has rules 10% of Price
                tax = price.multiply(new BigDecimal(0.1));
                break;
            case 2: // tobacco has rules 10 + (2% of Price)
                tax = new BigDecimal(10).add(price.multiply(new BigDecimal(0.02)));
                break;
            case 3: // entertainment has rules : 0 < Price < 100: tax-free | Price >= 100: 1% of ( Price - 100)
                if (price.compareTo(new BigDecimal(100)) >= 0) {
                    tax = price.subtract(new BigDecimal(100)).multiply(new BigDecimal(0.01));
                } else {
                    tax = new BigDecimal(0);
                }
                break;
            default:
                tax = new BigDecimal(0);
        }
        return tax.setScale(1, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * check order refundable or not
     * @param taxCode
     * @return
     */
    public Boolean isRefundable(Integer taxCode){
        Boolean refundable;
        switch (taxCode) {
            case 1: // food and beverage refundable
                refundable = Boolean.TRUE;
                break;
            case 2: // tobacco refundable = Boolean.TRUE;
                refundable = Boolean.FALSE;
                break;
            case 3: // entertainment refundable = Boolean.TRUE;
                refundable = Boolean.FALSE;
                break;
            default:
                refundable = Boolean.TRUE;
        }
        return refundable;
    }
}
