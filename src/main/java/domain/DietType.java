package domain;

import java.util.Objects;

public enum DietType {

  VEGETARIAN(true),
  REGULAR(false),
  VEGAN(true);
  private final boolean vegetarian;

  DietType(boolean vegetarian) {
    this.vegetarian = vegetarian;
  }

  public boolean isEquivalent(DietType d){
      if (Objects.requireNonNull(d) == DietType.VEGETARIAN) {
          return d.vegetarian == this.vegetarian;
      }
      return this.equals(d);
  }
}
