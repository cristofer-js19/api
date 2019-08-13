package com.restaurantorder.service;

import com.restaurantorder.util.RestaurantOrderUtil;
import com.restaurantorder.model.RestaurantOrderModel;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

/**
 * class RestaurantOrderServiceImpl
 */
public class RestaurantOrderServiceImpl implements RestaurantOrderService {

    /**
     * method getOutput()
     * @param input request that should be converted
     * @return request converted in a friendly text definition
     */
    public String getOutput(String input) {
        input = input.toLowerCase().trim().replace(" ","");
        if (!verifyBadInputStructure(input)) return "error";

        String timeOfDay = input.substring(0, input.indexOf(","));
        return getResultOutput(timeOfDay, input);
    }

    /**
     * method verifyBadInputStructure()
     * @param input request that should be converted
     * @return true if the request (input) contains incorrect structure
     */
    private boolean verifyBadInputStructure(String input) {
        return input.matches("^(morning|night)(\\,[\\d]+)*$");
    }

    /**
     * method getResultOutput()
     * @param timeOfDay period (morning/night)
     * @param input request that should be converted
     * @return request converted in a friendly text definition
     */
    private String getResultOutput(String timeOfDay, String input) {
        String[] selectedDishes = input.substring((input.indexOf(",") + 1)).split(",");

        List<RestaurantOrderModel> listDishes = new ArrayList<>();
        for (String s : selectedDishes) {
            listDishes.add(RestaurantOrderUtil.getDishNameByTimeOfDayAndType(timeOfDay, Integer.parseInt(s)));
        }

        listDishes.sort(nullsLast(comparing(RestaurantOrderModel::getDishType, nullsLast(naturalOrder()))));
        Map<RestaurantOrderModel, Integer> mapDishes = new TreeMap<>();
        Set<RestaurantOrderModel> quantityOfDishes = new TreeSet<>(listDishes);
        quantityOfDishes.stream().forEach((key) -> {
            mapDishes.put(key, Collections.frequency(listDishes, key));
        });

        return formatResultOutput(mapDishes);
    }

    /**
     * method formatResultOutput()
     * @param mapOutputDishes map containing the dish correspondent to the request
     * @return map converted in a friendly text definition
     */
    private String formatResultOutput(Map<RestaurantOrderModel, Integer> mapOutputDishes) {
        String result = mapOutputDishes.keySet().stream().map(key -> key.getDishName()).collect(Collectors.joining(", "));
        for (Map.Entry<RestaurantOrderModel, Integer> item : mapOutputDishes.entrySet()) {
            String dishName = item.getKey().getDishName();
            if (dishHasMoreThanOneOccurrenceAndIsNotError(item.getValue(), dishName)) {
                if (item.getKey().isCanBeRepeated()) {
                    result = result.replace(dishName, dishName + "(x" + item.getValue() + ")");
                } else {
                    result = result.substring(0, (result.indexOf(dishName) + dishName.length())) + ", error";
                }
            }
        }
        return result;
    }

    /**
     * method dishHasMoreThanOneOccurrenceAndIsNotError()
     * @param numberOfOccurrences quantity of occurrences
     * @param dishName name of the dish
     * @return true if there is more than one occurrence for the dish and the dishName is not "error"
     */
    private boolean dishHasMoreThanOneOccurrenceAndIsNotError(Integer numberOfOccurrences, String dishName) {
        return (numberOfOccurrences > 1 && !dishName.equals("error"));
    }
}