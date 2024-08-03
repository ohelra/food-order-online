package com.eatdaily.Onlineorderingfood.service;

import com.eatdaily.Onlineorderingfood.models.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
