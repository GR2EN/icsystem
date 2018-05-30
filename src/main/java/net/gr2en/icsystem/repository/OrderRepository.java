package net.gr2en.icsystem.repository;

import java.util.Date;
import java.util.List;
import net.gr2en.icsystem.model.Computer;
import net.gr2en.icsystem.model.Order;
import net.gr2en.icsystem.model.Service;
import net.gr2en.icsystem.model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> findByUser(User user);

  List<Order> findByComputer(Computer computer);

  List<Order> findByService(Service service);

  List<Order> findByDate(Date date);

}
