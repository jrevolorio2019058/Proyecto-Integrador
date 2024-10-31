package org.jrevolorio.api.proyectoIntegrador.service;

import org.jrevolorio.api.proyectoIntegrador.DTO.LoginDTO;
import org.jrevolorio.api.proyectoIntegrador.configuration.JwtAuthenticationFilter;
import org.jrevolorio.api.proyectoIntegrador.interfaces.UserRepository;
import org.jrevolorio.api.proyectoIntegrador.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUsuarioNoEncontrado() {

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("no Existo :l");
        loginDTO.setPassword("contraseña random");

        when(userRepository.findByUsername(loginDTO.getUsername())).thenReturn(null);

        String token = authService.authenticate(loginDTO);

        assertNull(token);
    }

    @Test
    public void testClaveIncorrecta() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("admin");
        loginDTO.setPassword("admi");

        User user = new User();
        user.setUsername("admin");
        user.setPassword("malasCredenciales");

        when(userRepository.findByUsername(loginDTO.getUsername())).thenReturn(user);

        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(false);

        String token = authService.authenticate(loginDTO);

        assertNull(token);
    }

    @Test
    public void testCredencialesNulas() {
        assertThrows(IllegalArgumentException.class, () -> {
            authService.authenticate(null);
        });
    }

    @Test
    public void testGenerarToken() {
        when(jwtAuthenticationFilter.generateToken("admin")).thenReturn("Bearer sampleToken");

        String token = jwtAuthenticationFilter.generateToken("admin");
        assertNotNull(token);
        assertTrue(token.startsWith("Bearer "));
    }

}
