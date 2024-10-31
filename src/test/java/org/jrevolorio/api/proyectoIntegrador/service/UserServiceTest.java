package org.jrevolorio.api.proyectoIntegrador.service;

import org.jrevolorio.api.proyectoIntegrador.interfaces.UserRepository;
import org.jrevolorio.api.proyectoIntegrador.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVerUsuarioPorDPI() {
        Long dpi = 12345678L;
        User user = new User();
        when(userRepository.findById(dpi)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserByDPI(dpi);
        assertTrue(foundUser.isPresent());

    }

    @Test
    public void testEliminarUsuario() {
        Long dpi = 12345678L;
        doNothing().when(userRepository).deleteById(dpi);
        userService.deleteUser(dpi);
        verify(userRepository, times(1)).deleteById(dpi);
    }

    @Test
    public void testObtenerUsuarios() {
        userService.getAllUsers();
        verify(userRepository, times(1)).findAll();
    }

}
