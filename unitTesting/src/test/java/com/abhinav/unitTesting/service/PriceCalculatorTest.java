package com.abhinav.unitTesting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PriceCalculatorTest {

    private PriceCalculator priceCalculator = new PriceCalculator();

    @BeforeEach
    void setUp()
    {
        System.out.println("Testing price calculator");
    }

    @Test
    void shouldApplyDiscountToPrice()
    {
        // arrange -> Initial input
        double price = 1000;
        double discount = 20;

        // act -> Action Performed
        double actualPrice = priceCalculator.calculatePrice(price , discount);

        // assertion --> Expected Result
        Assertions.assertEquals(800 , actualPrice);
    }

    @Test
    void shouldRejectDiscountAboveHundred()
    {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class ,
                ()-> priceCalculator.calculatePrice(1000,101));

        Assertions.assertEquals("Discount should be within 0 and 100" ,
                exception.getMessage());
    }
}
