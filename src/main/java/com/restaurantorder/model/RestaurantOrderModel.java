package com.restaurantorder.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

/**
 * class RestaurantOrderModel
 * Model used in the operations
 */
@Builder
@Getter
public class RestaurantOrderModel implements Comparable<RestaurantOrderModel>{

    private String timeOfDay;
    private int dishType;
    private String dishName;
    private boolean canBeRepeated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantOrderModel that = (RestaurantOrderModel) o;
        return Objects.equals(dishName, that.dishName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishName);
    }

    @Override
    public int compareTo(RestaurantOrderModel o) {
        return (int) (this.dishType - o.getDishType());
    }
}