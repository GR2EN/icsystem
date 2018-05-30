package net.gr2en.icsystem.service.impl;

import java.util.Date;
import java.util.List;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Computer;
import net.gr2en.icsystem.model.Order;
import net.gr2en.icsystem.model.User;
import net.gr2en.icsystem.repository.OrderRepository;
import net.gr2en.icsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository repository;

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private ComputerServiceImpl computerService;

  @Autowired
  private ServiceServiceImpl serviceService;

  @Override
  public List<Order> getAllOrders() {
    return repository.findAll();
  }

  @Override
  public Order getOrderById(Integer id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Order " + id + " not found."));
  }

  @Override
  public List<Order> getOrdersByUserId(Integer userId) {
    User user = userService.getUserById(userId);
    return repository.findByUser(user);
  }

  @Override
  public List<Order> getOrdersByComputerId(Integer computerId) {
    Computer computer = computerService.getComputerById(computerId);
    return repository.findByComputer(computer);
  }

  @Override
  public List<Order> getOrdersByServiceId(Integer serviceId) {
    net.gr2en.icsystem.model.Service service = serviceService.getServiceById(serviceId);
    return repository.findByService(service);
  }

  @Override
  public List<Order> getOrdersByDate(Date date) {
    return repository.findByDate(date);
  }

  @Override
  public Order add(Order order) {
    return repository.save(order);
  }

  @Override
  public Order edit(Integer id, Order details) {
    Order order = getOrderById(id);
    order.setUser(details.getUser());
    order.setComputer(details.getComputer());
    order.setService(details.getService());
    order.setHours(details.getHours());
    order.setDate(details.getDate());
    return repository.save(order);
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }
}
