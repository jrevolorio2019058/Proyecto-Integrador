package org.jrevolorio.api.proyectoIntegrador.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private Long DPI;
    private String username;
    private String password;
    private  String email;
    private int age;
    private String cellphone;

}
