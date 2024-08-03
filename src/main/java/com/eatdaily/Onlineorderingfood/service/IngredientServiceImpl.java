package com.eatdaily.Onlineorderingfood.service;

import com.eatdaily.Onlineorderingfood.models.IngredientsCategory;
import com.eatdaily.Onlineorderingfood.models.IngredientsItem;
import com.eatdaily.Onlineorderingfood.models.Restaurant;
import com.eatdaily.Onlineorderingfood.repository.IngredientCategoryRepository;
import com.eatdaily.Onlineorderingfood.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientsService{
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientsCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory ingredientsCategory = new IngredientsCategory();
        ingredientsCategory.setRestaurant(restaurant);
        ingredientsCategory.setName(name);
        return ingredientCategoryRepository.save(ingredientsCategory);
    }

    @Override
    public IngredientsCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> opt = ingredientCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient of category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {

       Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
       IngredientsCategory category = findIngredientCategoryById(categoryId);

       IngredientsItem item = new IngredientsItem();
       item.setName(ingredientName);
       item.setRestaurant(restaurant);
       item.setCategory(category);

       IngredientsItem ingredientsItem = ingredientItemRepository.save(item);
       category.getIngredients().add(ingredientsItem);
        return ingredientsItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem = ingredientItemRepository.findById(id);
        if(optionalIngredientsItem.isEmpty()){
            throw new Exception("Ingredient not found");
        }
        IngredientsItem ingredientsItem = optionalIngredientsItem.get();
        ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
        return ingredientItemRepository.save(ingredientsItem);
    }
}
