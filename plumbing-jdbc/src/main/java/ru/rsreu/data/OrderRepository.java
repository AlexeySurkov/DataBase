package ru.rsreu.data;

import ru.rsreu.classes.Order;

public interface OrderRepository {

  Order save(Order order);
  
}
