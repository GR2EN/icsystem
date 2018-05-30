package net.gr2en.icsystem.service.impl;

import java.util.List;
import net.gr2en.icsystem.exception.ResourceNotFoundException;
import net.gr2en.icsystem.model.User;
import net.gr2en.icsystem.repository.UsersRepository;
import net.gr2en.icsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
   private UsersRepository repository;

  @Override
  public List<User> getAllUsers() {
    return repository.findAll();
  }

  @Override
  public User getUserById(Integer id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found."));
  }

  @Override
  public User add(User user) {
    return repository.save(user);
  }

  @Override
  public User edit(Integer id, User details) {
    User user = getUserById(id);
    user.setFirstName(details.getFirstName());
    user.setLastName(details.getLastName());
    user.setEmail(details.getEmail());
    user.setPasswordHash(details.getPasswordHash());
    user.setRoles(details.getRoles());
    user.setOrders(details.getOrders());
    return repository.save(user);
  }

  @Override
  public void delete(Integer id) {
    repository.deleteById(id);
  }
}
