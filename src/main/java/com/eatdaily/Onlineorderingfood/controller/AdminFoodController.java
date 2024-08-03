package com.eatdaily.Onlineorderingfood.controller;

import com.eatdaily.Onlineorderingfood.models.Food;
import com.eatdaily.Onlineorderingfood.models.Restaurant;
import com.eatdaily.Onlineorderingfood.models.User;
import com.eatdaily.Onlineorderingfood.request.CreateFoodRequest;
import com.eatdaily.Onlineorderingfood.response.MessageResponse;
import com.eatdaily.Onlineorderingfood.service.FoodService;
import com.eatdaily.Onlineorderingfood.service.RestaurantService;
import com.eatdaily.Onlineorderingfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create-food")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req,req.getCategory(),restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-food/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Food deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update-food/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailibityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
