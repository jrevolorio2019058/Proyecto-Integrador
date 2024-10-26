package org.jrevolorio.api.proyectoIntegrador.service;

import org.jrevolorio.api.proyectoIntegrador.interfaces.UserRepository;
import org.jrevolorio.api.proyectoIntegrador.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService implements UserRepository {

    private final HashMap<Long, User> userStorage = new HashMap<>();

    @Override
    public User createUser(User user){

        Long id = Long.parseLong(String.valueOf(user.getDPI()));

        userStorage.put(id, user);

        return user;

    }

    @Override
    public User getUserByDPI(Long dpi){

        return userStorage.get(dpi);

    }

    @Override
    public List<User> getAllUsers(){

        return new ArrayList<>(userStorage.values());

    }

    @Override
    public User updateUser(Long dpi, User user){

        if (userStorage.containsKey(dpi)) {

            User existUser = userStorage.get(dpi);

            if(user.getUsername() != null){

                existUser.setUsername(user.getUsername());

            }

            if(user.getEmail() != null){

                existUser.setEmail(user.getEmail());

            }

            if(user.getPassword() != null){

                existUser.setPassword(user.getPassword());

            }

            if(user.getAge() != 0){

                existUser.setAge(user.getAge());

            }

            if(user.getCellphone() != null){

                existUser.setCellphone(user.getCellphone());

            }

            existUser.setDPI(dpi);

            userStorage.put(dpi, existUser);

            return existUser;

        }

        return null;

    }

    @Override
    public void deleteUser(Long dpi){

        userStorage.remove(dpi);

    }

    public boolean userExists(Long dpi){

        return userStorage.containsKey(dpi);

    }

}
