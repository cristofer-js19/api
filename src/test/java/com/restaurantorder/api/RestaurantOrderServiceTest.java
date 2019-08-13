package com.restaurantorder.api;

import com.restaurantorder.service.RestaurantOrderService;
import com.restaurantorder.service.RestaurantOrderServiceImpl;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * class RestaurantOrderServiceTest
 */
@RunWith(JUnitParamsRunner.class)
public class RestaurantOrderServiceTest {

    private RestaurantOrderService restaurantOrderService;

    @Before
    public void setUp() {
        this.restaurantOrderService = new RestaurantOrderServiceImpl();
    }

    @Test
    @Parameters(source = RestaurantOrderServiceTestProvider.class)
    public void orderShouldReturnTheCorrectResponse(String input, String output) {
        String expectedValue = this.restaurantOrderService.getOutput(input);

        Assert.assertEquals(expectedValue, output);
    }
}
