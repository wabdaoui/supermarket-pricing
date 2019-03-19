package com.supermarket.services.pricing;

import java.math.BigDecimal;

import com.supermarket.domain.Price;

/**
 * 3 for fixed price e.g discount 3 for £1
 */
public class DiscountPricing implements Pricing {

    private int bundleSize;
    private BigDecimal  bundlePrice;

    public DiscountPricing(int bundleSize, BigDecimal bundlePrice)
    {
        this.bundlePrice = bundlePrice;
        this.bundleSize = bundleSize;
    }

    @Override
    public BigDecimal calculate(BigDecimal quantity, Price price)
    {
        return calculateOffer(quantity).add(price.getValue().multiply(quantity.remainder(new BigDecimal(bundleSize))));

    }

    private BigDecimal calculateOffer(BigDecimal quantity)
    {
    	BigDecimal offerSum = BigDecimal.ZERO;

        if (quantity.intValue() > bundleSize)
        {
            offerSum = bundlePrice.multiply(quantity.divideToIntegralValue(new BigDecimal(bundleSize)));
        }

        return offerSum;
    }
}
