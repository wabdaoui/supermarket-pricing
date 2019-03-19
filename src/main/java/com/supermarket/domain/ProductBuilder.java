package com.supermarket.domain;

import com.supermarket.services.pricing.Pricing;

/**
 * A Builder class for building product objects.
 * A product can be associated with an id of any
 * type.
 *
 */
public class ProductBuilder {

        private Product product;

        public ProductBuilder()
        {
            product = new Product();
        }

        public ProductBuilder productID(Long id)
        {
            product.setId(id);
            return this;
        }

        public ProductBuilder name(String name)
        {
            product.setName(name);
            return this;
        }

        public ProductBuilder description(String description)
        {
            product.setDescription(description);
            return this;
        }

        public ProductBuilder price(Price price)
        {
            product.setPrice(price);
            return this;
        }

        public ProductBuilder pricing(Pricing pricing)
        {
            product.setPricing(pricing);
            return this;
        }

        public Product build()
        {
            return product;
        }
}
