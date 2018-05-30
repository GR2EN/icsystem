package net.gr2en.icsystem.service.impl;

import java.util.List;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Role;
import net.gr2en.icsystem.repository.RolesRepository;
import net.gr2en.icsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RolesRepository repository;

  @Override
  public List<Role> getAllRoles() {
    return repository.findAll();
  }

  @Override
  public Role getRoleById(Integer id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Role " + id + " not found."));
  }

  @Override
  public Role add(Role role) {
    return repository.save(role);
  }

  @Override
  public Role edit(Integer id, Role details) {
    Role role = getRoleById(id);
    role.setTitle(details.getTitle());
    role.setUsers(details.getUsers());
    return repository.save(role);
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }
}
