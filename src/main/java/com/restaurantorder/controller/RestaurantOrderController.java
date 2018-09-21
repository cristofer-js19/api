package com.restaurantorder.controller;

import com.restaurantorder.service.RestaurantOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantOrderController {

    @RequestMapping(value = "/order/{index}", method = RequestMethod.GET)
    public ResponseEntity<String> getSelectedDish(@PathVariable("index") String index) {
        RestaurantOrderService rs = new RestaurantOrderService();

        return new ResponseEntity<>(rs.getOutput(index), HttpStatus.OK);
    }
}
