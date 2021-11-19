package ru.rsreu.data;

import org.springframework.data.repository.CrudRepository;

import ru.rsreu.classes.SanitaryWare;

public interface IngredientRepository 
         extends CrudRepository<SanitaryWare, String> {

}
