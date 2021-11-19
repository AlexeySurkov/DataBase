package ru.rsreu.data;

import org.springframework.data.repository.CrudRepository;

import ru.rsreu.classes.Basket;

public interface TacoRepository 
         extends CrudRepository<Basket, Long> {

}
