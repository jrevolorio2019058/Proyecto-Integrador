package org.jrevolorio.api.proyectoIntegrador.interfaces;

import org.jrevolorio.api.proyectoIntegrador.model.User;

import java.util.List;

public interface UserRepository {

    User createUser(User user);
    User getUserByDPI(Long DPI);
    List<User> getAllUsers();
    User updateUser(Long DPI, User user);
    void deleteUser(Long DPI);

}
