package com.eatdaily.Onlineorderingfood.request;

import com.eatdaily.Onlineorderingfood.models.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restauranId;
    private Address deliveryAddress;
}
