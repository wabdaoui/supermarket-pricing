package com.supermarket.services.store;

import java.util.Optional;

import com.supermarket.domain.Product;

public interface ProductStore {

    public Optional<Product> get(Long t);

    public void add(Product product);

}
