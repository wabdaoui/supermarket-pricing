package com.supermarket.services.pricing;

import java.math.BigDecimal;

public interface Basket {
    public BigDecimal getTotal();
    public void add(Long productID, BigDecimal amount);
}
