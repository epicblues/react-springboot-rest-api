package com.example.gccoffee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EnumTest {

  @Test
  void valueOfTest() {
    assertThat(TestEnum.valueOf("TEST_ONE")).isEqualTo(TestEnum.TEST_ONE);
    assertThat(TestEnum.valueOf("TEST_TWO")).isEqualTo(TestEnum.TEST_TWO);
    assertThat(TestEnum.valueOf("TEST_THREE")).isEqualTo(TestEnum.TEST_THREE);
  }

  enum TestEnum {
    TEST_ONE,
    TEST_TWO,
    TEST_THREE
  }

}
