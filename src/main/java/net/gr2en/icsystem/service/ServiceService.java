package net.gr2en.icsystem.service;

import java.util.List;
import net.gr2en.icsystem.model.Service;

public interface ServiceService {

  List<Service> getAllServices();

  Service getServiceById(Integer id);

  Service add(Service service);

  Service edit(Integer id, Service details);

  void delete(Integer id);

  List<Service> getByPrice(double price);

  List<Service> getByPriceGreaterThan(double value);

  List<Service> getByPriceLessThan(double value);

}
