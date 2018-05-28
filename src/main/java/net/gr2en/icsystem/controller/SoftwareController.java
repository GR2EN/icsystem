package net.gr2en.icsystem.controller;

import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Software;
import net.gr2en.icsystem.repository.SoftwareRepository;
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
@RequestMapping("/api/software")
public class SoftwareController {

  @Autowired
  SoftwareRepository softwareRepository;

  /* get software list */
  @GetMapping("/list")
  public List<Software> getSoftware() {
    return softwareRepository.findAll();
  }

  /* get software by id */
  @GetMapping("/{id}")
  public Software getSoftwareById(@PathVariable(value = "id") Integer softwareId) {
    return softwareRepository.findById(softwareId)
        .orElseThrow(() -> new ResourceNotFoundException("Software", "id", softwareId));
  }

  /* add new software */
  @PostMapping("/new")
  public Software addNewSoftware(@Valid @RequestBody Software software) {
    return softwareRepository.save(software);
  }

  /* update software by id */
  @PutMapping("/update/{id}")
  public Software update(@PathVariable(value = "id") Integer softwareId,
      @Valid @RequestBody Software softwareDetails) {
    Software software = softwareRepository.findById(softwareId)
        .orElseThrow(() -> new ResourceNotFoundException("Software", "id", softwareId));

    software.setTitle(softwareDetails.getTitle());
    software.setComputers(softwareDetails.getComputers());
    Software updatedSoftware = softwareRepository.save(software);
    return updatedSoftware;
  }

  /* delete software by id */
  @DeleteMapping("/delete/{id}")
  public boolean delete(@PathVariable(value = "id") Integer softwareId) {
    Software software = softwareRepository.findById(softwareId)
        .orElseThrow(() -> new ResourceNotFoundException("Software", "id", softwareId));

    softwareRepository.delete(software);

    return true;
  }
}
