package com.eatdaily.Onlineorderingfood.request;

import com.eatdaily.Onlineorderingfood.models.Address;
import com.eatdaily.Onlineorderingfood.models.ContactInformation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
