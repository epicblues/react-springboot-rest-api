package com.example.gccoffee.model;

import java.util.Objects;
import java.util.regex.Pattern;
import lombok.Getter;
import org.springframework.util.Assert;

public class Email {

  @Getter
  private final String address;

  public Email(String address) {
    Assert.notNull(address, "address should not be null");
    Assert.isTrue(address.length() >= 4 && address.length() <= 50, "address length must be between 4 and 50");
    Assert.isTrue(checkAddress(address), "invalid email address");
    this.address = address;
  }

  private static boolean checkAddress(String address) {
    return Pattern.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", address);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Email email = (Email) o;
    return address.equals(email.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address);
  }

  @Override
  public String toString() {
    return "Email{" +
        "address='" + address + '\'' +
        '}';
  }
}
