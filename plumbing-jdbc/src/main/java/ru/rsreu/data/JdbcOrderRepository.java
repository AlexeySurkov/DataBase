// tag::core[]
package ru.rsreu.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.rsreu.classes.Order;
import ru.rsreu.classes.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

  private SimpleJdbcInsert orderInserter;
  private SimpleJdbcInsert orderTacoInserter;
  private ObjectMapper objectMapper;

  @Autowired
  public JdbcOrderRepository(JdbcTemplate jdbc) {
    this.orderInserter = new SimpleJdbcInsert(jdbc)
        .withTableName("SanitaryWare_Order")
        .usingGeneratedKeyColumns("id");

    this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
        .withTableName("SanitaryWare_Order_Basket");

    this.objectMapper = new ObjectMapper();
  }
// end::core[]

// tag::save[]
  @Override
  public Order save(Order order) {
    order.setPlacedAt(new Date());
    long orderId = saveOrderDetails(order);
    order.setId(orderId);
    List<Taco> tacos = order.getTacos();
    for (Taco taco : tacos) {
      saveTacoToOrder(taco, orderId);
    }

    return order;
  }

  private long saveOrderDetails(Order order) {
    @SuppressWarnings("unchecked")
    Map<String, Object> values =
        objectMapper.convertValue(order, Map.class);
    values.put("placedAt", order.getPlacedAt());

    return orderInserter
            .executeAndReturnKey(values)
            .longValue();
  }

  private void saveTacoToOrder(Taco taco, long orderId) {
    Map<String, Object> values = new HashMap<>();
    values.put("basketOrder", orderId);
    values.put("basket", taco.getId());
    orderTacoInserter.execute(values);
  }
// end::save[]

/*
// tag::core[]

...

// end::core[]
 */

// tag::core[]
}
// end::core[]
