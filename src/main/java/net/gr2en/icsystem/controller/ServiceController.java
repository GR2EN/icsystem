package net.gr2en.icsystem.controller;

import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Service;
import net.gr2en.icsystem.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

  @Autowired
  ServicesRepository servicesRepository;

  /* get services list */
  @GetMapping("/")
  public List<Service> getAllServices() {
    return servicesRepository.findAll();
  }

  /* get service by id */
  @GetMapping("/{id}")
  public Service getServiceById(@PathVariable(value = "id") Integer serviceId) {
    return servicesRepository.findById(serviceId)
        .orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
  }

  /* add new service */
  @PostMapping("/")
  public Service newService(@Valid @RequestBody Service service) {
    return servicesRepository.save(service);
  }

  /* update service by id */
  @PutMapping("/{id}")
  public Service update(@PathVariable(value = "id") Integer serviceId,
      @Valid @RequestBody Service serviceDetails) {
    Service service = servicesRepository.findById(serviceId)
        .orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));

    service.setTitle(serviceDetails.getTitle());
    service.setPrice(serviceDetails.getPrice());
    service.setOrders(serviceDetails.getOrders());
    Service updatedService = servicesRepository.save(service);
    return updatedService;
  }

  /* delete service by id */
  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable(value = "id") Integer serviceId) {
    Service service = servicesRepository.findById(serviceId)
        .orElseThrow(() -> new ResourceNotFoundException("Service", "id", serviceId));
    servicesRepository.delete(service);
    return true;
  }

  /* get services expensive than {price} */
  @PostMapping("/expensive/{price}")
  public List<Service> getServicesExpensiveThan(@PathVariable(value = "price") Double priceValue) {
    return servicesRepository.findByPriceGreaterThanEqual(priceValue);
  }

  /* get services cheaper than {price} */
  @PostMapping("/cheaper/{price}")
  public List<Service> getServicesCheaperThan(@PathVariable(value = "price") Double priceValue) {
    return servicesRepository.findByPriceLessThanEqual(priceValue);
  }

  /* get services with {price} */
  @PostMapping("/withPrice/{price}")
  public List<Service> getServicesWithPrice(@PathVariable(value = "price") Double priceValue) {
    return servicesRepository.findByPriceEquals(priceValue);
  }
}
