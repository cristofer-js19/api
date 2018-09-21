package com.restaurantorder.api;

public class RestaurantOrderServiceTestProvider {
    public static Object[] provideData() {
        return new Object[] {
                //Tests defined in the specification of the task
                new Object[]{"morning, 1, 2, 3", "eggs, toast, coffee"},
                new Object[]{"morning,2,1,3", "eggs, toast, coffee"},
                new Object[]{"morning,1,2,3,4", "eggs, toast, coffee, error"},
                new Object[]{"morning,1,2,3,3,3", "eggs, toast, coffee(x3)"},
                new Object[]{"night, 1, 2, 3, 4", "steak, potato, wine, cake"},
                new Object[]{"night,1,2,2,4", "steak, potato(x2), cake"},
                new Object[]{"night,1,2,3,5", "steak, potato, wine, error"},
                new Object[]{"night,1,1,2,3,5", "steak, error"},
                //Additional tests
                new Object[]{"morning1,1,2,3,5", "error"},
                new Object[]{"morning,1,2,3,10000", "eggs, toast, coffee, error"},
                new Object[]{"morning, 1000, 2, 3", "toast, coffee, error"},
                new Object[]{"morning, 1, 20, 3", "eggs, coffee, error"},
                new Object[]{"morning,1,1,2,3,3,3", "eggs, error"},
                new Object[]{"morning,1,2,2,3,3,3", "eggs, toast, error"},
                new Object[]{"night, 1, 2, 3, 3, 4", "steak, potato, wine, error"},
                new Object[]{"night, 1, 2, 3, 4, 4, 4", "steak, potato, wine, cake, error"},
                new Object[]{"night, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4", "steak, potato(x5), wine, cake, error"},
                new Object[]{"", "error"},
                new Object[]{"morning,2,1,3,", "error"},
                new Object[]{"morning,1,2,3,4,5", "eggs, toast, coffee, error"}
        };
    }
}