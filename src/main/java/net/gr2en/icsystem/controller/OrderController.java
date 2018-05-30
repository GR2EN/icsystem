package net.gr2en.icsystem.controller;

import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Order;
import net.gr2en.icsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders/")
public class OrderController {

  @Autowired
  OrderRepository repository;

  /* get all orders */
  @GetMapping("/list")
  public List<Order> getOrders() {
    return repository.findAll();
  }

  /* get order by id */
  @GetMapping("{id}")
  public Order getOrderById(@PathVariable(value = "id") Integer id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order " + id + " not found."));
  }

  /* add new order */
  @PostMapping("/new")
  public Order addNewOrder(@Valid @RequestBody Order order) {
    return repository.save(order);
  }

  /* delete an order by id */
  @DeleteMapping("/delete/{id}")
  public boolean deleteOrder(@PathVariable(value = "id") Integer id) {
    Order order = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order " + id + " not found."));

    repository.delete(order);
    return true;
  }
}
