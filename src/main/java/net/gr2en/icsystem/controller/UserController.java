package net.gr2en.icsystem.controller;

import java.util.List;
import javax.validation.Valid;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.User;
import net.gr2en.icsystem.repository.UsersRepository;
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
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  UsersRepository repository;

  /* get users list */
  @GetMapping("/")
  public List<User> getUsers() {
    return repository.findAll();
  }

  /* get user by id */
  @GetMapping("/{id}")
  public User getUserById(@PathVariable(value = "id") Integer userId) {
    return repository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
  }

  /* add new user */
  @PostMapping("/")
  public User newUser(@Valid @RequestBody User user) {
    return repository.save(user);
  }

  /* update user by id */
  @PutMapping("/{id}")
  public User update(@PathVariable(value = "id") Integer userId,
      @Valid @RequestBody User userDetails) {
    User user = repository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

    user.setFirstName(userDetails.getFirstName());
    user.setLastName(userDetails.getLastName());
    user.setEmail(userDetails.getEmail());
    user.setPasswordHash(userDetails.getPasswordHash());
    user.setRoles(userDetails.getRoles());
    user.setOrders(userDetails.getOrders());

    User updatedUser = repository.save(user);
    return updatedUser;
  }

  /* delete user by id */
  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable(value = "id") Integer userId) {
    User user = repository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

    repository.delete(user);

    return true;
  }
}
