package com.restaurantorder.util;

import com.restaurantorder.model.RestaurantOrderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantOrderUtil {

    private static List<RestaurantOrderModel> allDishes() {
        List<RestaurantOrderModel> list = new ArrayList<>();
        list.add(new RestaurantOrderModel("morning", 1, "eggs", false));
        list.add(new RestaurantOrderModel("morning", 2, "toast", false));
        list.add(new RestaurantOrderModel("morning", 3, "coffee", true));
        list.add(new RestaurantOrderModel("night", 1, "steak", false));
        list.add(new RestaurantOrderModel("night", 2, "potato", true));
        list.add(new RestaurantOrderModel("night", 3, "wine", false));
        list.add(new RestaurantOrderModel("night", 4, "cake", false));

        return list;
    }

    public static String getDishName(String timeOfDay, int indexRequested) {
        try {
            List<RestaurantOrderModel> result = allDishes().stream()
                    .filter(m -> m.getTimeOfDay().equals(timeOfDay) && m.getDishType() == indexRequested)
                    .collect(Collectors.toList());
            return result.get(0).getDishName();
        } catch (Exception e) {
            throw e;
        }
    }

    public static List<RestaurantOrderModel> getAllDishes() {
        return allDishes();
    }
}