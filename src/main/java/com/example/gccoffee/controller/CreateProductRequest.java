package com.example.gccoffee.controller;

import com.example.gccoffee.model.Category;
import lombok.Data;

@Data
public class CreateProductRequest {

  private String productName;
  private Category category;
  private long price;
  private String description;

}
