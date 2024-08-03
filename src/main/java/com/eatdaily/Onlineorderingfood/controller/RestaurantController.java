package com.eatdaily.Onlineorderingfood.controller;

import com.eatdaily.Onlineorderingfood.dto.RestaurantDto;
import com.eatdaily.Onlineorderingfood.models.Restaurant;
import com.eatdaily.Onlineorderingfood.models.User;
import com.eatdaily.Onlineorderingfood.request.CreateRestaurantRequest;
import com.eatdaily.Onlineorderingfood.service.RestaurantService;
import com.eatdaily.Onlineorderingfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestParam String keyword,
                                                            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Restaurant>> getAllRestaurant(@RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@RequestHeader("Authorization") String jwt,
                                                         @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/add-favorites/{id}")
    public ResponseEntity<RestaurantDto> addToFavorites(@RequestHeader("Authorization") String jwt,
                                                        @PathVariable Long id) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurantDto = restaurantService.addToFavorites(id,user);
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }
}
