package net.gr2en.icsystem.repository;

import java.util.List;
import net.gr2en.icsystem.model.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Service, Integer> {

  List<Service> findByPriceGreaterThanEqual(double priceValue);

  List<Service> findByPriceLessThanEqual(double priceValue);

  List<Service> findByPriceEquals(double priceValue);

}
