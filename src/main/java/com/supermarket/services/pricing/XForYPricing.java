package com.supermarket.services.pricing;

import java.math.BigDecimal;

import com.supermarket.domain.Price;

/**
 * 3 for 2 Pricing e.g 3 Fizzy Drinks for the price of two
 */
public class XForYPricing implements Pricing {

	private int bundle, payingBundle;

	public XForYPricing(int totalBundle, int payingBundle) {
		this.bundle = totalBundle;
		this.payingBundle = payingBundle;
	}

	@Override
	public BigDecimal calculate(BigDecimal quantity, Price price) {

		BigDecimal itemCost = price.getValue();
		BigDecimal pBundle = new BigDecimal(payingBundle);
		BigDecimal offerPrice = itemCost.multiply(pBundle);

		BigDecimal offerSum = BigDecimal.ZERO;

		if (quantity.intValue() > bundle) {

			offerSum = offerPrice.multiply(quantity.divideToIntegralValue(new BigDecimal(bundle)));
		}

		return offerSum.add(itemCost.multiply(quantity.remainder(new BigDecimal(bundle))));
	}

}
