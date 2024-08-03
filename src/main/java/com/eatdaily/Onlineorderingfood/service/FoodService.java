package com.eatdaily.Onlineorderingfood.service;

import com.eatdaily.Onlineorderingfood.models.Category;
import com.eatdaily.Onlineorderingfood.models.Food;
import com.eatdaily.Onlineorderingfood.models.Restaurant;
import com.eatdaily.Onlineorderingfood.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
    public void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain, boolean isNon, boolean isSeasonal, String foodCategory);
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailibityStatus(Long foodId) throws Exception;
}
