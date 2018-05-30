package net.gr2en.icsystem.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceAlreadyExistException;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Software;
import net.gr2en.icsystem.service.SoftwareService;
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
@RequestMapping("/software")
public class SoftwareController {

  @Autowired
  private SoftwareService softwareService;

  @GetMapping
  public ResponseEntity<List<Software>> getSoftware() {
    List<Software> software = softwareService.getAllSoftware();
    return ResponseEntity.ok(software);
  }

  @GetMapping("/{software_id}")
  public ResponseEntity<Software> getSoftwareById(@PathVariable(value = "software_id") Integer softwareId) {
    try {
      Software software = softwareService.getSoftwareById(softwareId);
      return ResponseEntity.ok(software);
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<Void> addSoftware(@Valid @RequestBody Software software)
      throws URISyntaxException {
    try {
      Software newSoftware = softwareService.add(software);
      return ResponseEntity.created(new URI("/software/" + newSoftware.getId())).build();
    } catch (ResourceAlreadyExistException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @PutMapping("/{software_id}")
  public Software update(@PathVariable(value = "software_id") Integer softwareId,
      @Valid @RequestBody Software details) {
    return softwareService.edit(softwareId, details);
  }

  @DeleteMapping("/{software_id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "software_id") Integer softwareId) {
    try {
      softwareService.delete(softwareId);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }
}
