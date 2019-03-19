package com.supermarket.domain;

import java.math.BigDecimal;

import com.supermarket.enums.Currency;

/**
 * This class represents the price of
 * an item in the Super Market.
 */
public class Price {

    private BigDecimal  value;

    private Currency currency;

    public Price(Currency currency, BigDecimal  value) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal  getValue() {
        return value;
    }

    public void setValue(BigDecimal  value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
