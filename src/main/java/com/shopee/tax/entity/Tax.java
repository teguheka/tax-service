package com.shopee.tax.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Table(name = "tax_object")
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonProperty("tax_code")
    private Integer taxCode;

    @Digits(integer = 9, fraction = 2)
    private BigDecimal price;

    @Transient
    private String type;

    @Transient
    private String refundable;

    @Transient
    private BigDecimal tax;

    @Transient
    private BigDecimal amount;

    public Tax() {
    }

    public Tax(String name, Integer taxCode, BigDecimal price) {
        this.name = name;
        this.taxCode = taxCode;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(Integer taxCode) {
        this.taxCode = taxCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRefundable() {
        return refundable;
    }

    public void setRefundable(String refundable) {
        this.refundable = refundable;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
