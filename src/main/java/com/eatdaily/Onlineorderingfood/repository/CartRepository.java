package com.eatdaily.Onlineorderingfood.repository;

import com.eatdaily.Onlineorderingfood.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    public Cart findByCustomerId(Long userId);
}
