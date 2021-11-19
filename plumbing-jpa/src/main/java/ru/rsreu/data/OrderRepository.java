package ru.rsreu.data;

import org.springframework.data.repository.CrudRepository;

import ru.rsreu.classes.Order;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
