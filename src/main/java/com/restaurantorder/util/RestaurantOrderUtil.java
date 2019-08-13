package com.restaurantorder.util;

import com.restaurantorder.model.RestaurantOrderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * class RestaurantOrderUtil
 * Contains methods for getting dishes (list and by time of day/type)
 */
public class RestaurantOrderUtil {

    /**
     * method allDishes()
     * @return list containing all possible dishes
     */
    private static List<RestaurantOrderModel> allDishes() {
        List<RestaurantOrderModel> list = new ArrayList<>();
        list.add(RestaurantOrderModel.builder().timeOfDay("morning").dishType(1).dishName("eggs").canBeRepeated(false).build());
        list.add(RestaurantOrderModel.builder().timeOfDay("morning").dishType(2).dishName("toast").canBeRepeated(false).build());
        list.add(RestaurantOrderModel.builder().timeOfDay("morning").dishType(3).dishName("coffee").canBeRepeated(true).build());
        list.add(RestaurantOrderModel.builder().timeOfDay("night").dishType(1).dishName("steak").canBeRepeated(false).build());
        list.add(RestaurantOrderModel.builder().timeOfDay("night").dishType(2).dishName("potato").canBeRepeated(true).build());
        list.add(RestaurantOrderModel.builder().timeOfDay("night").dishType(3).dishName("wine").canBeRepeated(false).build());
        list.add(RestaurantOrderModel.builder().timeOfDay("night").dishType(4).dishName("cake").canBeRepeated(false).build());

        return list;
    }

    /**
     * method getDishNameByTimeOfDayAndType()
     * @param timeOfDay period (morning or night)
     * @param indexRequested type of dish
     * @return dish that corresponds to the given timeOfDay and index
     */
    public static RestaurantOrderModel getDishNameByTimeOfDayAndType(String timeOfDay, int indexRequested) {
        return allDishes()
                .stream()
                .filter(m -> m.getTimeOfDay().equals(timeOfDay) && m.getDishType() == indexRequested)
                .findFirst()
                .orElse(RestaurantOrderModel.builder().timeOfDay(null).dishName("error").dishType((getMaxDishType(timeOfDay) + 1)).canBeRepeated(false).build());
    }

    /**
     * method getDishType()
     * @param timeOfDay period (morning or night)
     * @return the max type between the dishes
     */
    private static int getMaxDishType(String timeOfDay) {
        return allDishes()
                .stream()
                .filter(m -> m.getTimeOfDay().equals(timeOfDay))
                .mapToInt(RestaurantOrderModel::getDishType)
                .max()
                .getAsInt();
    }
}