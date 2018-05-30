package net.gr2en.icsystem.service;

import java.util.List;
import net.gr2en.icsystem.model.Role;

public interface RoleService {

  List<Role> getAllRoles();

  Role getRoleById(Integer id);

  Role add(Role role);

  Role edit(Integer id, Role details);

  void delete(Integer id);

}
