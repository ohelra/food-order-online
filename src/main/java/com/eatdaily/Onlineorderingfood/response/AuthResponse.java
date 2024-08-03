package com.eatdaily.Onlineorderingfood.response;

import com.eatdaily.Onlineorderingfood.models.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
