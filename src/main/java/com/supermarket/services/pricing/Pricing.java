package com.supermarket.services.pricing;

import java.math.BigDecimal;

import com.supermarket.domain.Price;

public interface Pricing {

	default BigDecimal calculate(BigDecimal quantity, Price price) {
		
		BigDecimal itemCost = price.getValue();
		return itemCost.multiply(quantity);
	}

}
