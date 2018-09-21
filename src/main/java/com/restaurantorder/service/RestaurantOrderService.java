package com.restaurantorder.service;

import com.restaurantorder.util.RestaurantOrderUtil;
import com.restaurantorder.model.RestaurantOrderModel;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class RestaurantOrderService implements IRestaurantOrderService {

    @Override
    public String getOutput(String input) {
        input = input.toLowerCase().trim().replace(" ","");

        if (!verifyBadInputStructure(input)) {
            return "error";
        }

        String timeOfDay = input.substring(0, input.indexOf(","));
        String output = defineInittialOutput(timeOfDay, input);

        return defineFinalOutput(timeOfDay, output);
    }

    private boolean verifyBadInputStructure(String input) {
        return input.matches("^(morning|night)(\\,[\\d]+)*$");
    }

    private String defineInittialOutput(String timeOfDay, String input) {
        String output = "";
        String[] selectedDishesStr = input.substring((input.indexOf(",") + 1), input.length()).split(",");
        int[] selectedDishes = Arrays.asList(selectedDishesStr).stream().mapToInt(Integer::parseInt).toArray();
        Arrays.sort(selectedDishes);

        try {
            for (int i = 0; i < selectedDishes.length; i++) {
                String selectedDish = RestaurantOrderUtil.getDishName(timeOfDay, selectedDishes[i]);
                output = (i == 0) ? output + selectedDish : output + ", " + selectedDish;
            }
        } catch (Exception e) {
            return output + ", error";
        }

        return output;
    }

    private String defineFinalOutput(String timeOfDay, String output) {
        for (RestaurantOrderModel selectedDish : RestaurantOrderUtil.getAllDishes()) {
            String outputTemp = "";

            if (selectedDish.getTimeOfDay().equals(timeOfDay)) {
                String dish = selectedDish.getDishName();
                int firstOcurrence = output.indexOf(dish);
                int lastOcurrence = output.lastIndexOf(dish);

                if (firstOcurrence != lastOcurrence) {
                    if (selectedDish.getCanBeRepeated()) {
                        outputTemp = output.substring(firstOcurrence, (lastOcurrence + dish.length()));
                        output = output.replace(outputTemp, dish + "(x" + StringUtils.countMatches(output, dish) + ")");
                    } else {
                        outputTemp = output.substring(firstOcurrence, output.length());
                        output = output.replace(outputTemp, dish + ", error");
                    }
                }
            }
        }
        return output;
    }
}