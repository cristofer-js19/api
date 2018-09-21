package com.restaurantorder.model;

public class RestaurantOrderModel {

    private String timeOfDay;
    private int dishType;
    private String dishName;
    private boolean canBeRepeated;

    public RestaurantOrderModel(String timeOfDay, int dishType, String dishName, boolean canBeRepeated) {
        this.timeOfDay = timeOfDay;
        this.dishType = dishType;
        this.dishName = dishName;
        this.canBeRepeated = canBeRepeated;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public int getDishType() {
        return dishType;
    }

    public String getDishName() {
        return dishName;
    }

    public boolean getCanBeRepeated() {
        return canBeRepeated;
    }
}
