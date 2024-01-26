package net.thumbtack.buscompany.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {
  ADMIN("Admin"),
  CLIENT("Client");
  private final String value;
}
