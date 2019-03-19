package com.supermarket.services.pricing;

import java.math.BigDecimal;

import com.supermarket.domain.Price;

/**
 * Unit pricing e.g Cheese £1.99 per 100g
 */
public class UnitPricing implements Pricing
{
	BigDecimal unitQuantity;

    public UnitPricing(BigDecimal unitQuantity)
    {
        this.unitQuantity = unitQuantity;
    }

    @Override
    public BigDecimal calculate(BigDecimal quantity, Price price)
    {
        return quantity.divideToIntegralValue(unitQuantity).multiply(price.getValue());
    }
}
