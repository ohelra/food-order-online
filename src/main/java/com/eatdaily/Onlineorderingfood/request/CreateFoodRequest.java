package com.eatdaily.Onlineorderingfood.request;

import com.eatdaily.Onlineorderingfood.models.Category;
import com.eatdaily.Onlineorderingfood.models.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegetarin;
    private boolean session;
    private List<IngredientsItem> ingredientsItems;
}
