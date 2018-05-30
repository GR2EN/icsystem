package net.gr2en.icsystem.service;

import java.util.List;
import net.gr2en.icsystem.model.Computer;

public interface ComputerService {

  List<Computer> getAllComputers();

  Computer getComputerById(Integer id);

  Computer add(Computer computer);

  Computer edit(Integer id, Computer details);

  void delete(Integer id);

  List<Computer> getFreeComputers();

  List<Computer> getBusyComputers();

}
