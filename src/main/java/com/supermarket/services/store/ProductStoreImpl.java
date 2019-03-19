package com.supermarket.services.store;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.supermarket.domain.Product;

/**
 * A synchronized cache that can be used as a product
 * store. This is an implementation of the
 * {@link com.supermarket.services.ProductStore.class}
 * class.
 */
public class ProductStoreImpl implements ProductStore {

    private Map<Long, Product> products;
    
	/** Holder */
	private static class SingletonHolder {
		/** Preinitialized unique Instance */
		private final static ProductStoreImpl instance = new ProductStoreImpl();
	}

	public static ProductStoreImpl getInstance() {
		return SingletonHolder.instance;
	}

    private ProductStoreImpl() {
        this.products = new ConcurrentHashMap<Long, Product>();
    }

	@Override
    public Optional<Product> get(Long t) {
        return Optional.ofNullable(products.get(t));
    }
	
	@Override
    public void add(Product product) {
        if (null != product) products.put(product.getId(), product);
    }
}
