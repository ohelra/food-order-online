package com.eatdaily.Onlineorderingfood.repository;

import com.eatdaily.Onlineorderingfood.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
