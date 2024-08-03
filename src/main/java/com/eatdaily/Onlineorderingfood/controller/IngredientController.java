package com.eatdaily.Onlineorderingfood.controller;

import com.eatdaily.Onlineorderingfood.models.IngredientsCategory;
import com.eatdaily.Onlineorderingfood.models.IngredientsItem;
import com.eatdaily.Onlineorderingfood.request.IngredientCategoryRequest;
import com.eatdaily.Onlineorderingfood.request.IngredientRequest;
import com.eatdaily.Onlineorderingfood.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientsCategory(@RequestBody IngredientCategoryRequest req) throws Exception {
        IngredientsCategory ingredientsCategory = ingredientsService.createIngredientCategory(req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.CREATED);
    }

    @PostMapping("/items")
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest req) throws Exception{
        IngredientsItem ingredientsItem = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(),req.getCategoryId());
        return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<IngredientsItem> updateIngredientStock(@PathVariable Long id) throws Exception{
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(@PathVariable Long id) throws Exception{
        List<IngredientsItem> items = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/category/{id}")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientByCategory(@PathVariable Long id) throws Exception{
        List<IngredientsCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
