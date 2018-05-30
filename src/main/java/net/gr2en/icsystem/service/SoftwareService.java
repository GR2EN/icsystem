package net.gr2en.icsystem.service;

import java.util.List;
import net.gr2en.icsystem.model.Software;

public interface SoftwareService {

  List<Software> getAllSoftware();

  Software getSoftwareById(Integer id);

  Software add(Software software);

  Software edit(Integer id, Software details);

  void delete(Integer id);

}
