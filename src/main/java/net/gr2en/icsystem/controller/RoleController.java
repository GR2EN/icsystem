package net.gr2en.icsystem.controller;

import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Role;
import net.gr2en.icsystem.repository.RolesRepository;
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
@RequestMapping("/api/roles")
public class RoleController {

  @Autowired
  RolesRepository repository;

  /* get roles list */
  @GetMapping("/")
  public List<Role> getRoles() {
    return repository.findAll();
  }

  /* get role by id */
  @GetMapping("/{id}")
  public Role getRoleById(@PathVariable(value = "id") Integer roleId) {
    return repository.findById(roleId)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
  }

  /* add new role */
  @PostMapping("/")
  public Role newRole(@Valid @RequestBody Role role) {
    return repository.save(role);
  }

  /* update role by id */
  @PutMapping("/{id}")
  public Role update(@PathVariable(value = "id") Integer roleId,
      @Valid @RequestBody Role roleDetails) {
    Role role = repository.findById(roleId)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

    role.setTitle(roleDetails.getTitle());
    Role updatedRole = repository.save(role);
    return updatedRole;
  }

  /* delete role by id */
  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable(value = "id") Integer roleId) {
    Role role = repository.findById(roleId)
        .orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));

    repository.delete(role);

    return true;
  }
}
