package com.eatdaily.Onlineorderingfood.repository;

import com.eatdaily.Onlineorderingfood.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT s FROM Restaurant s WHERE lower(s.name) LIKE lower(concat('%',:query,'%')) OR lower(s.cuisineType) LIKE lower(concat('%',:query,'%'))")
    public List<Restaurant> findBySearchQuery(String query);
    public Restaurant findByOwnerId(Long userId);
}
