package net.gr2en.icsystem.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceAlreadyExistException;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.Role;
import net.gr2en.icsystem.service.RoleService;
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
@RequestMapping("/roles")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @GetMapping
  public ResponseEntity<List<Role>> getRoles() {
    List<Role> roles = roleService.getAllRoles();
    return ResponseEntity.ok(roles);
  }

  @GetMapping("/{role_id}")
  public ResponseEntity<Role> getRoleById(@PathVariable(value = "role_id") Integer roleId) {
    try {
      Role role = roleService.getRoleById(roleId);
      return ResponseEntity.ok(role);
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<Void> addRole(@Valid @RequestBody Role role) throws URISyntaxException {
    try {
      Role newRole = roleService.add(role);
      return ResponseEntity.created(new URI("/roles/" + newRole.getId())).build();
    } catch (ResourceAlreadyExistException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @PutMapping("/{role_id}")
  public Role update(@PathVariable(value = "role_id") Integer roleId,
      @Valid @RequestBody Role details) {
    return roleService.edit(roleId, details);
  }

  @DeleteMapping("/{role_id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "role_id") Integer roleId) {
    try {
      roleService.delete(roleId);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }
}
