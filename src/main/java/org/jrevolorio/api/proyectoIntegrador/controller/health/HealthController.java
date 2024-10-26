package org.jrevolorio.api.proyectoIntegrador.controller.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectoIntegrador/v1")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> checkApi() {

        return ResponseEntity.ok("<h1>The API is working ok!</h1>");

    }

}
