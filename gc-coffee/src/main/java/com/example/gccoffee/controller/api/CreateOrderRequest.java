package com.example.gccoffee.controller.api;

import com.example.gccoffee.model.OrderItem;
import java.util.List;
import lombok.Data;

@Data
public class CreateOrderRequest {

  private List<OrderItem> orderItems;
  private String email;
  private String address;
  private String postcode;
}
