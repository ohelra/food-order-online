package com.eatdaily.Onlineorderingfood.service;

import com.eatdaily.Onlineorderingfood.models.Cart;
import com.eatdaily.Onlineorderingfood.models.CartItem;
import com.eatdaily.Onlineorderingfood.models.User;
import com.eatdaily.Onlineorderingfood.request.CartItemRequest;

public interface CartService {
    public CartItem addItemToCart(CartItemRequest req, String jwt) throws Exception;
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;
    public Long calculateCartTotals(Cart cart) throws Exception;
    public Cart findCartById(Long id) throws Exception;
    public Cart findCartByUserId(Long userId) throws Exception;
    public Cart clearCart(Long userId) throws Exception;
}
