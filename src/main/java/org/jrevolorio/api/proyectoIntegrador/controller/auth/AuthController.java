package org.jrevolorio.api.proyectoIntegrador.controller.auth;

import jakarta.servlet.http.HttpServletResponse;
import org.jrevolorio.api.proyectoIntegrador.DTO.LoginDTO;
import org.jrevolorio.api.proyectoIntegrador.configuration.JwtAuthenticationFilter;
import org.jrevolorio.api.proyectoIntegrador.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/proyectoIntegrador/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.authenticate(loginDTO);

        if (token != null) {

            Map<String, String> response = new HashMap<>();
            response.put("message", "Logeo Exitoso");
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body("ERROR | CREDENCIALES ERRONEAS");
        }
    }

}
