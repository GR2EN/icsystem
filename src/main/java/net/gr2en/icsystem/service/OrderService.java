package net.gr2en.icsystem.service;

import java.util.Date;
import java.util.List;
import net.gr2en.icsystem.model.Order;

public interface OrderService {

  List<Order> getAllOrders();

  Order getOrderById(Integer id);

  List<Order> getOrdersByUserId(Integer userId);

  List<Order> getOrdersByComputerId(Integer computerId);

  List<Order> getOrdersByServiceId(Integer serviceId);

  List<Order> getOrdersByDate(Date date);

  Order add(Order order);

  Order edit(Integer id, Order details);

  void delete(Integer id);

}
