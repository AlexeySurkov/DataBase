package ru.rsreu.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.rsreu.classes.SanitaryWare;
import ru.rsreu.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, SanitaryWare> {

  private IngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }
  
  @Override
  public SanitaryWare convert(String id) {
    Optional<SanitaryWare> optionalIngredient = ingredientRepo.findById(id);
	return optionalIngredient.isPresent() ?
		   optionalIngredient.get() : null;
  }

}
