package ru.rsreu.classes;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {
  
  private final String id;
  private final String name;
  private final Type type;

  public enum Type {
    BATH, FAUCET, SINK, TOILET
  }
}
