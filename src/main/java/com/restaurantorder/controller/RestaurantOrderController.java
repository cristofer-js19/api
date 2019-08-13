package com.restaurantorder.controller;

import com.restaurantorder.service.RestaurantOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * class RestaurantOrderController
 */
@RestController
public class RestaurantOrderController {

    @Autowired
    private RestaurantOrderService restaurantOrderService;

    @RequestMapping(value = "/order/{index}", method = RequestMethod.GET)
    public ResponseEntity<String> getSelectedDish(@PathVariable("index") String index) {
        return new ResponseEntity<>(this.restaurantOrderService.getOutput(index), HttpStatus.OK);
    }
}
