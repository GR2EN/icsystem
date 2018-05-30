package net.gr2en.icsystem.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceAlreadyExistException;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Computer;
import net.gr2en.icsystem.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/computers")
public class ComputerController {

  @Autowired
  private ComputerService computerService;

  @GetMapping
  public ResponseEntity<List<Computer>> getComputers() {
    List<Computer> computers =  computerService.getAllComputers();
    return ResponseEntity.ok(computers);
  }

  @GetMapping("/{computer_id}")
  public ResponseEntity<Computer> getComputerById(@PathVariable(value = "computer_id") Integer computerId) {
    try {
      Computer computer = computerService.getComputerById(computerId);
      return ResponseEntity.ok(computer);
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<Void> addComputer(@Valid @RequestBody Computer computer) throws URISyntaxException {
    try {
      Computer newComputer = computerService.add(computer);
      return ResponseEntity.created(new URI("/computers/" + newComputer.getId())).build();
    } catch (ResourceAlreadyExistException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @PutMapping("/{computer_id}")
  public Computer edit(@PathVariable(name = "computer_id") Integer computerId,
      @Valid @RequestBody Computer details) {
    return computerService.edit(computerId, details);
  }

  @DeleteMapping("/{computer_id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "computer_id") Integer computerId) {
    try {
      computerService.delete(computerId);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/free")
  public List<Computer> getFreeComputers() {
    return computerService.getFreeComputers();
  }

  @PostMapping("/busy")
  public List<Computer> getBusyComputers() {
    return computerService.getBusyComputers();
  }

}
