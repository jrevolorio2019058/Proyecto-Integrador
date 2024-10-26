package org.jrevolorio.api.proyectoIntegrador.controller.user;

import org.jrevolorio.api.proyectoIntegrador.model.User;
import org.jrevolorio.api.proyectoIntegrador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectoIntegrador/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.createUser(user));

    }

    @GetMapping("/getUser/{DPI}")
    public ResponseEntity<?> getUser(@PathVariable Long DPI) {

        if(DPI == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("DPI cannot be null");

        }

        User user = userService.getUserByDPI(DPI);

        if( user != null){

            return ResponseEntity.ok(user);

        }else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with DPI: " + DPI);

        }

    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());

    }

    @PutMapping("/updateUser/{DPI}")
    public ResponseEntity<?> updateUser(@PathVariable Long DPI, @RequestBody User user) {

        if (DPI == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("DPI cannot be null");
        }

        User updateUser = userService.updateUser(DPI, user);

        if(updateUser != null){

            return ResponseEntity.ok(updateUser);

        }else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with DPI: " + DPI);

        }

    }

    @DeleteMapping("/deleteUser/{DPI}")
    public ResponseEntity<?> deleteUser(@PathVariable Long DPI) {

        if (DPI == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("DPI cannot be null");
        }

        if (!userService.userExists(DPI)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with DPI: " + DPI);
        }

        userService.deleteUser(DPI);
        return ResponseEntity.noContent().build();

    }

}
