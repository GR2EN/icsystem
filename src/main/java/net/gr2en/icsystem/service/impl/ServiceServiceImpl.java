package net.gr2en.icsystem.service.impl;

import java.util.List;
import net.gr2en.icsystem.model.Service;
import net.gr2en.icsystem.repository.ServicesRepository;
import net.gr2en.icsystem.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

  @Autowired
  private ServicesRepository repository;

  @Override
  public List<Service> getAllServices() {
    return null;
  }

  @Override
  public Service getServiceById(Integer id) {
    return null;
  }

  @Override
  public Service add(Service service) {
    return null;
  }

  @Override
  public Service edit(Integer id, Service details) {
    return null;
  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public List<Service> getByPrice(double price) {
    return null;
  }

  @Override
  public List<Service> getByPriceGreaterThan(double value) {
    return null;
  }

  @Override
  public List<Service> getByPriceLessThan(double value) {
    return null;
  }
}
