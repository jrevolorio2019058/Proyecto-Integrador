package org.jrevolorio.api.proyectoIntegrador.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long DPI;
    private String username;
    private String password;
    private  String email;
    private int age;
    private String cellphone;

}
