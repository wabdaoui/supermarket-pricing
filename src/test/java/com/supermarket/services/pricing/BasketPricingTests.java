package com.supermarket.services.pricing;

import com.supermarket.domain.Price;
import com.supermarket.domain.Product;
import com.supermarket.domain.ProductBuilder;
import com.supermarket.enums.Currency;
import com.supermarket.services.pricing.Basket;
import com.supermarket.services.pricing.BasketImpl;
import com.supermarket.services.store.ProductStore;
import com.supermarket.services.store.ProductStoreImpl;
import com.supermarket.services.pricing.DiscountPricing;
import com.supermarket.services.pricing.SinglePricing;
import com.supermarket.services.pricing.UnitPricing;
import com.supermarket.services.pricing.XForYPricing;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

public class BasketTests {

    private static final ProductStore store = ProductStoreImpl.getInstance();

    private static final long PHILADELPHIA_ID = 0;
    private static final long PIZZA_ID = 1;
    private static final long BEN_AND_JERRYS_ID = 2;
    private static final long CHEDDAR_ID = 3;

    private static final BigDecimal PHILADELPHIA_PRICE = new BigDecimal(3.00);
    private static final BigDecimal PIZZA_PRICE = new BigDecimal(5.00);
    private static final BigDecimal BEN_AND_JERRYS_PRICE = new BigDecimal(6.00);
    private static final BigDecimal CHEDDAR_PRICE_PER_300 = new BigDecimal(10.00);

    private static final BigDecimal GRAMS_300 = new BigDecimal(300);


    @BeforeClass
    public static void fixture()
    {
        long id = 0;
        Product philadelphia =
                new ProductBuilder()
                        .productID(id)
                        .name("Philadelphia cheese")
                        .description("The most tasty cheese ever")
                        .price(new Price(Currency.GBP, PHILADELPHIA_PRICE))
                        .pricing(new SinglePricing()).build();

        Product pizza =
                new ProductBuilder()
                        .productID(++id)
                        .name("Pepperoni Pizza")
                        .description("Pizza with Pepperoni and mushrooms")
                        .price(new Price(Currency.GBP, PIZZA_PRICE))
                        .pricing(new XForYPricing(3, 2))
                        .build();

        Product benAndJerrys =
                new ProductBuilder()
                        .productID(++id)
                        .name("Ben & Jerrys")
                        .description("Ben & Jerrys ice cream")
                        .price(new Price(Currency.GBP, BEN_AND_JERRYS_PRICE))
                        .pricing(new DiscountPricing(3, new BigDecimal(5.0)))
                        .build();

        Product cheddarCheese =
                new ProductBuilder()
                        .productID(++id)
                        .name("Cheddar Cheese")
                        .description("Soft and Light cheddar cheese")
                        .price(new Price(Currency.GBP, CHEDDAR_PRICE_PER_300))
                        .pricing(new UnitPricing(GRAMS_300))
                        .build();

        store.add(philadelphia);
        store.add(pizza);
        store.add(benAndJerrys);
        store.add(cheddarCheese);
    }

    @Test
    public void thatCalculationOfSinglePricingIsCorrect()
    {
    	BigDecimal quantity = new BigDecimal(4);

        Basket basket = new BasketImpl(store);
        basket.add(PHILADELPHIA_ID, quantity);

        assertEquals(quantity.multiply(PHILADELPHIA_PRICE) , basket.getTotal());
    }

    @Test
    public void thatCalculationOfXForYPricingIsCorrect()
    {
    	BigDecimal quantity = new BigDecimal(4);

        Basket basket = new BasketImpl(store);
        basket.add(PIZZA_ID, quantity);

        assertEquals(quantity.subtract(BigDecimal.ONE).multiply(PIZZA_PRICE) , basket.getTotal());
    }

    @Test
    public void thatCalculationOfDiscountPricingIsCorrect()
    {
    	BigDecimal quantity = new BigDecimal(4);

        Basket basket = new BasketImpl(store);
        basket.add(BEN_AND_JERRYS_ID, quantity);

        assertEquals(new BigDecimal(11.0) , basket.getTotal());
    }

    @Test
    public void thatCalculationOfUnitPricingIsCorrect()
    {
    	BigDecimal quantity = new BigDecimal(600);

        Basket basket = new BasketImpl(store);
        basket.add(CHEDDAR_ID, quantity);

        assertEquals(quantity.divideToIntegralValue(GRAMS_300) .multiply(CHEDDAR_PRICE_PER_300), basket.getTotal());
    }
}
