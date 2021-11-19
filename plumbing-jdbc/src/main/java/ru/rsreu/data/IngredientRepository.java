package ru.rsreu.data;

import ru.rsreu.classes.Ingredient;

public interface IngredientRepository {

  Iterable<Ingredient> findAll();
  
  Ingredient findById(String id);
  
  Ingredient save(Ingredient ingredient);
  
}
