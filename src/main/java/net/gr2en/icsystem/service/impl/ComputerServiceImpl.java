package net.gr2en.icsystem.service.impl;

import java.util.List;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Computer;
import net.gr2en.icsystem.repository.ComputerRepository;
import net.gr2en.icsystem.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerServiceImpl implements ComputerService {

  @Autowired
  private ComputerRepository repository;

  @Override
  public List<Computer> getAllComputers() {
    return repository.findAll();
  }

  @Override
  public Computer getComputerById(Integer id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Computer " + id + " not found."));
  }

  @Override
  public Computer add(Computer computer) {
    return repository.save(computer);
  }

  @Override
  public Computer edit(Integer id, Computer details) {
    Computer computer = getComputerById(id);
    computer.setStatus(details.getStatus());
    computer.setLastMaintenance(details.getLastMaintenance());
    computer.setOrders(details.getOrders());
    computer.setSoftware(details.getSoftware());
    return repository.save(computer);
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }

  @Override
  public List<Computer> getFreeComputers() {
    return repository.findByStatus("free");
  }

  @Override
  public List<Computer> getBusyComputers() {
    return repository.findByStatus("busy");
  }
}
