package com.supermarket.domain;

import com.supermarket.services.pricing.Pricing;

/**
 * This is a model class that represents a product.
 *
 */
public class Product {

    private Long id;

    private String name;

    private String description;

    private Price price;

    private Pricing pricing;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing offer) {
        this.pricing = offer;
    }
}
