package net.gr2en.icsystem.controller;

import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Computer;
import net.gr2en.icsystem.repository.ComputerRepository;
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
@RequestMapping("/api/computers")
public class ComputerController {

  private final String FREE_STATUS = "free";
  private final String BUSY_STATUS = "busy";

  @Autowired
  ComputerRepository repository;

  /* get computers list */
  @GetMapping("/")
  public List<Computer> getAllComputers() {
    return repository.findAll();
  }

  /* get computer by id */
  @GetMapping("/{id}")
  public Computer getComputerById(@PathVariable(value = "id") Integer computerId) {
    return repository.findById(computerId)
        .orElseThrow(() -> new ResourceNotFoundException("Computer", "id", computerId));
  }

  /* add new computer */
  @PostMapping("/")
  public Computer newComputer(@Valid @RequestBody Computer computer) {
    return repository.save(computer);
  }

  /* update computer by id */
  @PutMapping("/{id}")
  public Computer update(@PathVariable(value = "id") Integer computerId,
      @Valid @RequestBody Computer computerDetails) {
    Computer computer = repository.findById(computerId)
        .orElseThrow(() -> new ResourceNotFoundException("Computer", "id", computerId));

    computer.setStatus(computerDetails.getStatus());
    computer.setLastMaintenance(computerDetails.getLastMaintenance());
    computer.setSoftware(computerDetails.getSoftware());
    computer.setOrders(computerDetails.getOrders());

    Computer updatedComputer = repository.save(computer);
    return updatedComputer;
  }

  /* delete computer by id */
  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable(value = "id") Integer computerId) {
    Computer computer = repository.findById(computerId)
        .orElseThrow(() -> new ResourceNotFoundException("Computer", "id", computerId));

    repository.delete(computer);
    return true;
  }

  /* get free computers */
  @PostMapping("/free")
  public List<Computer> getFreeComputers() {
    return repository.findByStatus(FREE_STATUS);
  }

  /* get busy computers */
  @PostMapping("/busy")
  public List<Computer> getBusyComputers() {
    return repository.findByStatus(BUSY_STATUS);
  }
}
