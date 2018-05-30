package net.gr2en.icsystem.service.impl;

import java.util.List;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Software;
import net.gr2en.icsystem.repository.SoftwareRepository;
import net.gr2en.icsystem.service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoftwareServiceImpl implements SoftwareService {

  @Autowired
  private SoftwareRepository repository;

  @Override
  public List<Software> getAllSoftware() {
    return repository.findAll();
  }

  @Override
  public Software getSoftwareById(Integer id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Software " + id + " not found."));
  }

  @Override
  public Software add(Software software) {
    return repository.save(software);
  }

  @Override
  public Software edit(Integer id, Software details) {
    Software software = getSoftwareById(id);
    software.setTitle(details.getTitle());
    software.setComputers(details.getComputers());
    return repository.save(software);
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }
}
