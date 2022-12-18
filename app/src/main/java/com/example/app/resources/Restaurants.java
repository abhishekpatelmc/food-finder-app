package com.example.app.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.example.app.models.RestaurantItem;
import com.example.app.services.FoodServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Restaurants {

    private final FoodServices foodServices;

    Restaurants(FoodServices foodServices) {
        this.foodServices = foodServices;
    }
    @GetMapping("/restaurantitems")
    public Set<RestaurantItem> getRestaurantItems(@RequestParam("searchWords") String searchTerm) throws Exception {
        List<String> list = new ArrayList<>(Arrays.asList(searchTerm.split(" ")));
        return foodServices.getRestaurantItems(list);
    }
  
}