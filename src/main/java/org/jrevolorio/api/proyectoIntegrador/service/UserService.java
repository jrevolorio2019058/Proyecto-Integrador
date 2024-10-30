package org.jrevolorio.api.proyectoIntegrador.service;

import jakarta.annotation.PostConstruct;
import org.jrevolorio.api.proyectoIntegrador.configuration.SecurityConfig;
import org.jrevolorio.api.proyectoIntegrador.interfaces.UserRepository;
import org.jrevolorio.api.proyectoIntegrador.model.User;
import org.springdoc.core.service.SecurityService;
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

    @Autowired
    private SecurityConfig securityConfig;

    @PostConstruct
    public void createDefaultUser() {

        String defaultUsername = "admin";
        String defaultPassword = "admin";
        Long defaultDPI = 1234567891234L;

        if (!userRepository.existsById(defaultDPI)) {
            User defaultUser = new User();
            defaultUser.setUsername(defaultUsername);
            defaultUser.setPassword(securityConfig.passwordEncoder().encode(defaultPassword));
            defaultUser.setDPI(defaultDPI);
            defaultUser.setEmail("admin@example.com");
            defaultUser.setAge(30);
            defaultUser.setCellphone("12345678");

            userRepository.save(defaultUser);
            System.out.println("Usuario por defecto creado: " + defaultUsername);
        } else {
            System.out.println("El usuario por defecto ya existe.");
        }

    }

    public User createUser(User user) {

        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));

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
                existingUser.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
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