package org.jrevolorio.api.proyectoIntegrador.service;

import org.jrevolorio.api.proyectoIntegrador.interfaces.UserRepository;
import org.jrevolorio.api.proyectoIntegrador.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByDPI(Long dpi) {
        return userRepository.findById(dpi);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long dpi, User user) {

        Optional<User> existingUserOpt = userRepository.findById(dpi);

        if (existingUserOpt.isPresent()) {

            User existingUser = existingUserOpt.get();

            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getAge() != 0) {
                existingUser.setAge(user.getAge());
            }
            if (user.getCellphone() != null) {
                existingUser.setCellphone(user.getCellphone());
            }

            return userRepository.save(existingUser);

        }
        return null;
    }

    public void deleteUser(Long dpi) {
        userRepository.deleteById(dpi);
    }

    public boolean userExists(Long dpi) {
        return userRepository.existsById(dpi);
    }

}