package com.example.gccoffee.service;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductService implements ProductService {

  private final ProductRepository repository;

  public DefaultProductService(ProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Product> getProductsByCategory(Category category) {
    return repository.findByCategory(category);
  }

  @Override
  public List<Product> getAllProducts() {
    return repository.findAll();
  }

  @Override
  public Product createProduct(String productName, Category category, long price) {
    var newProduct = new Product(UUID.randomUUID(), productName, category, price);
    return repository.insert(newProduct);
  }

  @Override
  public Product createProduct(String productName, Category category, long price, String description) {
    var newProduct = new Product(UUID.randomUUID(), productName, category, price, description, LocalDateTime.now(), LocalDateTime.now());
    return repository.insert(newProduct);
  }
}
