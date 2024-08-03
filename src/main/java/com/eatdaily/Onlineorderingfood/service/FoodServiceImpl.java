package com.eatdaily.Onlineorderingfood.service;

import com.eatdaily.Onlineorderingfood.models.Category;
import com.eatdaily.Onlineorderingfood.models.Food;
import com.eatdaily.Onlineorderingfood.models.Restaurant;
import com.eatdaily.Onlineorderingfood.repository.FoodRepository;
import com.eatdaily.Onlineorderingfood.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;
    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredientsItems());
        food.setSeasonal(req.isSession());
        food.setVegetarian(req.isVegetarin());

        Food sfood = foodRepository.save(food);

        restaurant.getFoods().add(sfood);
        return sfood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain, boolean isNon, boolean isSeasonal, String foodCategory) {
       
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
       
        if(isVegitarain){
            foods = filterByVegetarin(foods, isVegitarain);
        }
        if(isNon){
            foods = filterByNonveg(foods, isNon);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory != null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }
        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory() != null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNon) {
        return foods.stream().filter(food -> food.isVegetarian() == false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarin(List<Food> foods, boolean isVegitarain) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegitarain).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if(optionalFood.isEmpty()){
            throw new Exception("Food not exist...");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailibityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
