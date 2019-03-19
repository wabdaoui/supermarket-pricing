package com.supermarket.services.store;

import com.supermarket.services.store.ProductStore;
import com.supermarket.services.store.ProductStoreImpl;
import com.supermarket.services.pricing.SinglePricing;
import com.supermarket.domain.Price;
import com.supermarket.domain.Product;
import com.supermarket.domain.ProductBuilder;
import com.supermarket.enums.Currency;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

public class ProductStoreTests {

    @Test
    public void thatCanAddToProductStore()
    {

        Product product =
                new ProductBuilder()
                        .productID(1l)
                        .description("Test Description")
                        .name("Pizza")
                        .price(new Price(Currency.GBP, new BigDecimal(10)))
                        .pricing(new SinglePricing()).build();

        ProductStore store = ProductStoreImpl.getInstance();
        store.add(product);

        assertNotNull(store.get(product.getId()));

    }

}
