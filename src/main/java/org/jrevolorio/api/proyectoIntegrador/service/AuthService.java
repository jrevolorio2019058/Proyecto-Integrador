package org.jrevolorio.api.proyectoIntegrador.service;

import org.jrevolorio.api.proyectoIntegrador.DTO.LoginDTO;
import org.jrevolorio.api.proyectoIntegrador.configuration.JwtAuthenticationFilter;
import org.jrevolorio.api.proyectoIntegrador.interfaces.UserRepository;
import org.jrevolorio.api.proyectoIntegrador.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticate(LoginDTO loginDTO) {

        User user = userRepository.findByUsername(loginDTO.getUsername());

        if(user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {

            return jwtAuthenticationFilter.generateToken(user.getUsername());

        }

        return null;

    }

}
