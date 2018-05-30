package net.gr2en.icsystem.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceAlreadyExistException;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.User;
import net.gr2en.icsystem.service.UserService;
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
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<User> getUserById(@PathVariable(value = "user_id") Integer userId) {
    try {
      User user = userService.getUserById(userId);
      return ResponseEntity.ok(user);
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @PostMapping
  public ResponseEntity<Void> addComputer(@Valid @RequestBody User user) throws URISyntaxException {
    try {
      User newUser = userService.add(user);
      return ResponseEntity.created(new URI("/users/" + newUser.getId())).build();
    } catch (ResourceAlreadyExistException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }

  @PutMapping("/{user_id}")
  public ResponseEntity<Void> update(@PathVariable(value = "user_id") Integer userId,
      @Valid @RequestBody User userDetails) {
    try {
      userService.edit(userId, userDetails);
      return ResponseEntity.noContent().build();
    } catch(ResourceNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{user_id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "user_id") Integer userId) {
    try {
      userService.delete(userId);
      return ResponseEntity.noContent().build();
    } catch (ResourceNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }
}
