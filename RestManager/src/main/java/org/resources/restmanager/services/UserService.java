package org.resources.restmanager.services;


import org.resources.restmanager.model.entities.auth.Role;
import org.resources.restmanager.model.entities.auth.User;
import org.resources.restmanager.repositories.auth.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    AppUserRepository userRepository;

    public User addUser(User user){
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("TEACHER"));
        user.setRoles(roles);
        return userRepository.save(user);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public boolean deleteUser(Long id){
        try{
            User u = userRepository.getReferenceById(id);
            userRepository.delete(u);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
