package com.example.gccoffee.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public class Product {

  private final UUID productId;
  private final LocalDateTime createdAt;
  private String productName;
  private Category category;
  private long price;
  private String description;
  private LocalDateTime updatedAt;

  public Product(UUID productId, String productName, Category category, long price) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Product(UUID productId, String productName, Category category, long price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.productId = productId;
    this.productName = productName;
    this.category = category;
    this.price = price;
    this.description = description;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return productId.equals(product.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId);
  }

  public UUID getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
    this.updatedAt = LocalDateTime.now();
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.updatedAt = LocalDateTime.now();
    this.category = category;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
    this.updatedAt = LocalDateTime.now();
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
    this.updatedAt = LocalDateTime.now();
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

}
