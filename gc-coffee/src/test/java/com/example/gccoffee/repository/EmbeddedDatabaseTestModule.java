package com.example.gccoffee.repository;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public abstract class EmbeddedDatabaseTestModule {

  protected final static MySQLContainer mysql = new MySQLContainer<>(
      DockerImageName
          .parse("mysql:8.0.28-debian"))
      .withDatabaseName("gc-coffee")
      .withInitScript("schema.sql")
      .withUsername("test")
      .withPassword("test")
      .withCommand("--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci");

  private static DataSource testDataSource = null;

  protected static DataSource getDataSource() {
    if (testDataSource == null) {
      testDataSource = DataSourceBuilder.create().type(HikariDataSource.class)
          .username(mysql.getUsername())
          .password(mysql.getPassword())
          .url(mysql.getJdbcUrl())
          .build();
    }
    return testDataSource;
  }
}
