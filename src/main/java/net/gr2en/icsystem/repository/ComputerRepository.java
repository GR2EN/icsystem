package net.gr2en.icsystem.repository;

import java.util.List;
import net.gr2en.icsystem.model.Computer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {

  List<Computer> findByStatus(String status);
}
