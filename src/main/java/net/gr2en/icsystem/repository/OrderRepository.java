package net.gr2en.icsystem.repository;

import net.gr2en.icsystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
