package org.jrevolorio.api.proyectoIntegrador.controller.user;

import org.jrevolorio.api.proyectoIntegrador.model.User;
import org.jrevolorio.api.proyectoIntegrador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proyectoIntegrador/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }

    @GetMapping("/getUser/{dpi}")
    public ResponseEntity<?> getUser(@PathVariable Long dpi) {

        if (dpi == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)

                    .body("DPI no puede ser Nulo");

        }

        Optional<User> userOpt = userService.getUserByDPI(dpi);

        if (userOpt.isPresent()) {

            return ResponseEntity.ok(userOpt.get());

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)

                    .body("Usuario no encontrado con DPI: " + dpi);

        }
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);

    }

    @PutMapping("/updateUser/{dpi}")
    public ResponseEntity<?> updateUser(@PathVariable Long dpi, @RequestBody User user) {

        if (dpi == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)

                    .body("DPI no puede ser Nulo");

        }

        User updatedUser = userService.updateUser(dpi, user);

        if (updatedUser != null) {

            return ResponseEntity.ok("Usuario Actualizado Correctamente");

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)

                    .body("Usuario no encontrado con DPI: " + dpi);

        }
    }

    @DeleteMapping("/deleteUser/{dpi}")
    public ResponseEntity<?> deleteUser(@PathVariable Long dpi) {

        if (dpi == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)

                    .body("DPI cannot be null");

        }

        if (!userService.userExists(dpi)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)

                    .body("User not found with DPI: " + dpi);

        }

        userService.deleteUser(dpi);

        return ResponseEntity.noContent().build();

    }

}
