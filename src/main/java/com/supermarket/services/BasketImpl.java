package com.supermarket.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.supermarket.domain.Price;
import com.supermarket.domain.Product;
import com.supermarket.exceptions.ProductNotExistException;
import com.supermarket.services.pricing.Pricing;

/**
 * This is an implementation of {@link com.supermarket.services.Basket.class}
 */
public class BasketImpl implements Basket {

	private Map<Long, BigDecimal> basketStore = null;
	private ProductStore store;

	public BasketImpl(ProductStore store) {
		this.basketStore = new HashMap<Long, BigDecimal>();
		this.store = store;
	}
}
