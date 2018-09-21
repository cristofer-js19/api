package com.restaurantorder.api;

import com.restaurantorder.service.RestaurantOrderService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RestaurantOrderServiceTest {

    @Test
    @Parameters(source = RestaurantOrderServiceTestProvider.class)
    public void orderShouldReturnTheCorrectResponse(String input, String output) {
        RestaurantOrderService r = new RestaurantOrderService();
        String expectedValue = r.getOutput(input);

        Assert.assertEquals(expectedValue, output);
    }
}
