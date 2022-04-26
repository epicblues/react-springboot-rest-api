package com.example.gccoffee.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

class ProductJdbcRepositoryTest extends EmbeddedDatabaseTestModule {

  private static final Product newProduct = new Product(UUID.randomUUID(), "new-product", Category.COFFEE_BEAN_PACKAGE, 1000L);
  private static ProductJdbcRepository repository;

  @BeforeAll
  static void setup() {
    if (!mysql.isRunning()) {
      mysql.start();
    }
    repository = new ProductJdbcRepository(new NamedParameterJdbcTemplate(getDataSource()));
  }

  @BeforeEach
  void beforeEach() {
    repository.insert(newProduct);
  }

  @AfterEach
  void cleanUp() {
    repository.deleteAll();
  }

  @Test
  @DisplayName("상품을 추가할 수 있다.")
  void testInsert() {
    Product secondNewProduct = new Product(UUID.randomUUID(), "old-product", Category.COFFEE_BEAN_PACKAGE, 2000L);
    repository.insert(secondNewProduct);
    var products = repository.findAll();
    assertThat(products).isNotEmpty().hasSize(2);
  }

  @Test
  @DisplayName("상품을 카테고리로 조회할 수 있다.")
  void testFindByCategory() {
    var searchedProducts = repository.findByCategory(newProduct.getCategory());
    assertThat(searchedProducts).isNotEmpty().allMatch(product -> product.getCategory().equals(newProduct.getCategory()));
  }

  @Test
  @DisplayName("상품을 Id로 조회할 수 있다.")
  void testFindById() {
    var searchedProduct = repository.findById(newProduct.getProductId());
    assertThat(searchedProduct).isNotEmpty().get().isEqualTo(newProduct);
  }

  @Test
  @DisplayName("상품을 이름으로 조회할 수 있다.")
  void testFindByName() {
    var searchedProduct = repository.findByName(newProduct.getProductName());
    assertThat(searchedProduct).isNotEmpty().get().isEqualTo(newProduct);
  }
}
