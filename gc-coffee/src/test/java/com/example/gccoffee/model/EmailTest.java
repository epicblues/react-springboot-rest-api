package com.example.gccoffee.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class EmailTest {

  @Test
  void testInvalidEmail() {
    assertThatThrownBy(() -> {
      var email = new Email("account");
    }).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void testValidEmail() {
    assertThatNoException().isThrownBy(() -> {
      var email = new Email("account@hanmail.net");
    });
  }
  @Test
  void testEmailEquality() {

    var email = new Email("account@hanmail.net");
    var email2 = new Email("account@hanmail.net");

    assertThat(email).isEqualTo(email2);
  }
}
