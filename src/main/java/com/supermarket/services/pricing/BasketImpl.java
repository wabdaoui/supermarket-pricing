package com.supermarket.services.pricing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.supermarket.domain.Price;
import com.supermarket.domain.Product;
import com.supermarket.exceptions.ProductNotExistException;
import com.supermarket.services.pricing.Pricing;
import com.supermarket.services.store.ProductStore;

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
	
	@Override
	public BigDecimal getTotal() {
		return basketStore.keySet().stream().map(id -> calculateSumForThisProduct(id, basketStore.get(id)))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	@Override
	public void add(Long productID, BigDecimal quantity) {
		basketStore.merge(productID, quantity, (id,qt)-> qt.add(quantity));
	}

	private BigDecimal calculateSumForThisProduct(Long productID, BigDecimal amount) {
		Optional<Product> product = store.get(productID);
		Pricing pricing = product.map(Product::getPricing).orElseThrow(ProductNotExistException::new);
		Price price = product.map(Product::getPrice).orElseThrow(ProductNotExistException::new);
		return pricing.calculate(amount, price);
	}

}
