package com.example.gccoffee.repository;

import static com.example.gccoffee.JdbcUtils.toLocalDateTime;
import static com.example.gccoffee.JdbcUtils.toUUID;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJdbcRepository implements ProductRepository {

  private static final Logger logger = LoggerFactory.getLogger(ProductJdbcRepository.class);

  private static final RowMapper<Product> productRowMapper = (resultSet, i) -> {
    var productId = toUUID(resultSet.getBytes("product_id"));
    var productName = resultSet.getString("product_name");
    var category = Category.valueOf(resultSet.getString("category"));
    var price = resultSet.getLong("price");
    var description = resultSet.getString("description");
    var createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
    var updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));

    return new Product(productId, productName, category, price, description, createdAt, updatedAt);
  };
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private static Map<String, Object> toParamMap(Product product) {
    var paramMap = new HashMap<String, Object>();
    paramMap.put("productId", product.getProductId().toString().getBytes(StandardCharsets.UTF_8));
    paramMap.put("productName", product.getProductName());
    paramMap.put("category", product.getCategory().toString());
    paramMap.put("price", product.getPrice());
    paramMap.put("description", product.getDescription());
    paramMap.put("createdAt", product.getCreatedAt());
    paramMap.put("updatedAt", product.getUpdatedAt());
    return paramMap;
  }


  @Override
  public List<Product> findAll() {
    return jdbcTemplate.query("SELECT * FROM products", productRowMapper);

  }

  @Override
  public Product insert(Product product) {
    var updated = jdbcTemplate.update("INSERT INTO products("
        + "product_id, product_name, category, price, description, created_at, updated_at) "
        + "values (UUID_TO_BIN(:productId), :productName,:category,:price,:description,:createdAt,:updatedAt)", toParamMap(product));
    if (updated != 1) {
      throw new RuntimeException("Nothing was inserted");
    }
    return product;
  }

  @Override
  public Product update(Product product) {
    return null;
  }

  @Override
  public Optional<Product> findById(UUID productId) {
    try {
      var product = jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_id = UUID_TO_BIN(:productId)",
          Map.of("productId", productId.toString().getBytes(StandardCharsets.UTF_8)), productRowMapper);
      return Optional.ofNullable(product);
    } catch (DataAccessException exception) {
      logger.info(exception.getClass().getSimpleName(), exception);
      return Optional.empty();
    }

  }

  @Override
  public Optional<Product> findByName(String productName) {
    try {
      var product = jdbcTemplate.queryForObject("SELECT * FROM products WHERE product_name = :productName",
          Map.of("productName", productName), productRowMapper);
      return Optional.ofNullable(product);
    } catch (DataAccessException exception) {
      logger.info(exception.getClass().getSimpleName(), exception);
      return Optional.empty();
    }
  }

  @Override
  public List<Product> findByCategory(Category category) {
    return jdbcTemplate.query("SELECT * FROM products WHERE category = :category",
        Map.of("category", category.toString()), productRowMapper);
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update("DELETE FROM products", Collections.emptyMap());
  }
}
