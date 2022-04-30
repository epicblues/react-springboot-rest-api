package com.example.gccoffee.model;

import java.util.UUID;
import lombok.Data;

@Data
public class OrderItem {

  private UUID productId;
  private Category category;
  private long price;
  private int quantity;

}
