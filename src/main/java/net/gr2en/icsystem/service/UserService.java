package net.gr2en.icsystem.service;

import java.util.List;
import net.gr2en.icsystem.model.User;

public interface UserService {

  List<User> getAllUsers();

  User getUserById(Integer id);

  User add(User user);

  User edit(Integer id, User details);

  void delete(Integer id);

}
